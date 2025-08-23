package com.example.stock.controller

import com.example.stock.model.DividendAndPreferential
import com.example.stock.service.DividendAndPreferentialService
import com.example.stock.service.StockService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

@Controller
class DividendAndPreferentialController(
    private val dividendAndPreferentialService: DividendAndPreferentialService,
    private val stockService: StockService
) {
    @GetMapping("/dividend-and-preferential/register")
    fun showRegistrationForm(model: Model): String {
        model.addAttribute("stocks", stockService.findAll())
        return "dividend_and_preferential_form"
    }

    @PostMapping("/dividend-and-preferential/register")
    fun register(
        @RequestParam("stockId") stockId: Int,
        @RequestParam("dividendPrice") dividendPrice: Int,
        @RequestParam("fee") fee: Int,
        @RequestParam("transactionAt") transactionAt: String
    ): String {
        val stock = stockService.findById(stockId)
        if (stock == null) {
            throw IllegalArgumentException("Stock not found: $stockId")
        }
        val dividendAndPreferential = DividendAndPreferential(
            stock = stock,
            dividendPrice = dividendPrice,
            fee = fee,
            transactionAt = LocalDate.parse(transactionAt)
        )
        dividendAndPreferentialService.save(dividendAndPreferential)
        return "redirect:/dividend-and-preferential"
    }

    @GetMapping("/dividend-and-preferential")
    fun showDividendAndPreferentialList(model: Model): String {
        model.addAttribute("dividendAndPreferentials", dividendAndPreferentialService.findAll())
        return "dividend_and_preferential"
    }
}
