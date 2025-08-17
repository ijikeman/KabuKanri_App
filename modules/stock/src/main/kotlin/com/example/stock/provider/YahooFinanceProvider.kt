package com.example.stock.provider

import org.jsoup.Jsoup
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

@Component
class YahooFinanceProvider : FinanceProvider {

    companion object {
        // Yahoo!ファイナンスのURL。{code}部分を銘柄コードに置換して使用する。
        private const val BASE_URL = "https://finance.yahoo.co.jp/quote"
    }

    /**
     * 指定された銘柄コードの株価情報をYahoo!ファイナンスから取得します。
     *
     * @param code 銘柄コード
     * @param country 国コード (現在は"jp"のみ対応)
     * @return 取得した株価情報を含むStockInfoオブジェクト。対応していない国や情報取得に失敗した場合はnullを返す。
     */
    override fun fetchStockInfo(code: String, country: String): StockInfo? {
        // 現在は日本の銘柄のみを対象とする
        if (country.lowercase() != "jp") {
            return null
        }

        return try {
            // Jsoupを使用してWebサイトに接続し、HTMLドキュメントを取得
            val url = "$BASE_URL/$code.T"
            val doc = Jsoup.connect(url).get()

            // 各情報を抽出する
            val price = extractPrice(doc)
            val dividend = extractDividend(doc)
            val earningsDate = extractEarningsDate(doc)

            StockInfo(price, dividend, earningsDate)
        } catch (e: Exception) {
            // 接続エラーやパースエラーが発生した場合はnullを返す
            e.printStackTrace()
            null
        }
    }

    /**
     * HTMLドキュメントから株価を抽出します。
     * @param doc JsoupでパースしたHTMLドキュメント
     * @return 抽出した株価。見つからない場合はnull。
     */
    private fun extractPrice(doc: org.jsoup.nodes.Document): Int? {
        // 株価は `StyledNumber__value__3rXW` というCSSクラスを持つspan要素に含まれている
        val priceText = doc.select(".StyledNumber__value__3rXW").first()?.text()
        // "1,234"のようなカンマ区切りの数値をパースしてIntに変換
        return priceText?.replace(",", "")?.toIntOrNull()
    }

    /**
     * HTMLドキュメントから1株あたりの配当金を抽出します。
     * @param doc JsoupでパースしたHTMLドキュメント
     * @return 抽出した配当金。見つからない場合はnull。
     */
    private fun extractDividend(doc: org.jsoup.nodes.Document): Double? {
        // "1株配当（会社予想）"というテキストを持つdt要素を探し、その次のdd要素から値を取得
        val dividendElement = doc.select("dt:contains(1株配当（会社予想）)").first()?.nextElementSibling()
        val dividendText = dividendElement?.text()
        // "95.00円" のようなテキストから数値部分のみを抽出
        return dividendText?.filter { it.isDigit() || it == '.' }?.toDoubleOrNull()
    }

    /**
     * HTMLドキュメントから次回の業績発表日を抽出します。
     * @param doc JsoupでパースしたHTMLドキュメント
     * @return 抽出した業績発表日。見つからない場合はnull。
     */
    private fun extractEarningsDate(doc: org.jsoup.nodes.Document): LocalDate? {
        // "直近の決算発表日は" というテキストを含むp要素を探す
        val earningsElement = doc.select("p:contains(直近の決算発表日は)").first()
        val earningsText = earningsElement?.text() ?: return null

        // "直近の決算発表日は2025年8月7日でした。" というテキストから日付を正規表現で抽出
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
