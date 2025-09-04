package com.example.stock.provider

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.regex.Pattern

/**
 * Yahoo!ファイナンスから株価情報を取得するプロバイダーです。
 *
 * @property requestDelayMillis リクエスト間の遅延時間（ミリ秒）
 */
@Component
class YahooFinanceProvider(
    @Value("\${yahoo.finance.request.delay.millis:1000}")
    private val requestDelayMillis: Long
) : FinanceProvider {

    companion object {
        /** Yahoo!ファイナンスのベースURL */
        private const val BASE_URL = "https://finance.yahoo.co.jp/quote"
        /** ページ状態を保持するJavaScript変数の接頭辞 */
        private const val PRELOADED_STATE_PREFIX = "window.__PRELOADED_STATE__ = "
    }

    private val objectMapper = ObjectMapper()

    /**
     * 指定された銘柄コードの株価情報を取得します。
     *
     * @param code 銘柄コード
     * @param country 国コード (現在は "jp" のみサポート)
     * @return 取得した株価情報 (StockInfo)。取得に失敗した場合は null。
     */
    override fun fetchStockInfo(code: String, country: String): StockInfo? {
        Thread.sleep(requestDelayMillis)

        if (country.lowercase() != "jp") {
            return null
        }

        return try {
            val url = "$BASE_URL/$code.T"
            val doc = Jsoup.connect(url).get()
            val preloadedState = getPreloadedState(doc)

            val price = extractPrice(preloadedState)
            val dividend = extractDividend(preloadedState)
            val earningsDate = extractEarningsDate(preloadedState)

            StockInfo(price, dividend, earningsDate)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 指定された銘柄コードの企業名を取得します。
     *
     * @param code 銘柄コード
     * @param country 国コード (現在は "jp" のみサポート)
     * @return 取得した企業名。取得に失敗した場合は null。
     */
    override fun fetchStockName(code: String, country: String): String? {
        Thread.sleep(requestDelayMillis)

        if (country.lowercase() != "jp") {
            return null
        }

        return try {
            val url = "$BASE_URL/$code.T"
            val doc = Jsoup.connect(url).get()
            val preloadedState = getPreloadedState(doc)
            extractName(preloadedState)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * HTMLドキュメントから、ページの状態を含むJSONデータを抽出します。
     *
     * @param doc JsoupでパースしたHTMLドキュメント
     * @return ページ状態のJSONデータを表すJsonNode。見つからない場合は null。
     */
    private fun getPreloadedState(doc: org.jsoup.nodes.Document): JsonNode? {
        val scriptElements = doc.getElementsByTag("script")
        val script = scriptElements.find { it.html().trim().startsWith(PRELOADED_STATE_PREFIX) }
            ?: return null
        val jsonText = script.html().trim().removePrefix(PRELOADED_STATE_PREFIX)
        return objectMapper.readTree(jsonText)
    }

    /**
     * JSONデータから企業名を抽出します。
     *
     * @param jsonNode ページ状態のJSONデータ
     * @return 企業名。見つからない場合は null。
     */
    private fun extractName(jsonNode: JsonNode?): String? {
        return jsonNode?.at("/mainStocksPriceBoard/priceBoard/name")?.asText()
    }

    /**
     * JSONデータから株価を抽出します。
     *
     * @param jsonNode ページ状態のJSONデータ
     * @return 株価。見つからない場合は null。
     */
    private fun extractPrice(jsonNode: JsonNode?): Double? {
        return jsonNode?.at("/mainStocksPriceBoard/priceBoard/price")?.asDouble()
    }

    /**
     * JSONデータから1株当たりの配当金を抽出します。
     * "dps" は "Dividend Per Share" (1株当たり配当) の略です。
     *
     * @param jsonNode ページ状態のJSONデータ
     * @return 1株当たりの配当金。見つからない、または数値に変換できない場合は null。
     */
    private fun extractDividend(jsonNode: JsonNode?): Double? {
        // "dps" in referenceIndex seems to be "Dividend Per Share"
        return jsonNode?.at("/mainStocksDetail/referenceIndex/dps")?.asText()?.toDoubleOrNull()
    }

    /**
     * JSONデータから決算発表日を抽出します。
     *
     * @param jsonNode ページ状態のJSONデータ
     * @return 決算発表日。見つからない、または解析できない場合は null。
     */
    private fun extractEarningsDate(jsonNode: JsonNode?): LocalDate? {
        val earningsText = jsonNode?.at("/mainStocksPressReleaseSchedule/pressReleaseScheduleMessage")?.asText()
            ?: return null

        val pattern = Pattern.compile("(\\d{4})年(\\d{1,2})月(\\d{1,2})日")
        val matcher = pattern.matcher(earningsText)

        return if (matcher.find()) {
            val year = matcher.group(1).toInt()
            val month = matcher.group(2).toInt()
            val day = matcher.group(3).toInt()
            LocalDate.of(year, month, day)
        } else {
            null
        }
    }
}
