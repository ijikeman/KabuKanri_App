package com.example.stock.controller

import com.example.stock.model.DividendAndPreferential
import com.example.stock.service.DividendAndPreferentialService
import com.example.stock.service.HoldingsService
import com.example.stock.service.OwnerService
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
    private val stockService: StockService,
    private val ownerService: OwnerService,
    private val holdingsService: HoldingsService
) {
    @GetMapping("/dividend-and-preferential/register")
    fun showRegistrationForm(@RequestParam(name = "ownerId", required = false) ownerId: Int?, model: Model): String {
        model.addAttribute("owners", ownerService.findAll())
        if (ownerId != null) {
            model.addAttribute("holdings", holdingsService.getHoldingsForOwner(ownerId))
            model.addAttribute("selectedOwnerId", ownerId)
        }
        return "dividend_and_preferential_form"
    }

    @PostMapping("/dividend-and-preferential/register")
    fun register(
        @RequestParam("holdingId") holdingId: Int,
        @RequestParam("dividendPrice") dividendPrice: Int,
        @RequestParam("fee") fee: Int,
        @RequestParam("transactionAt") transactionAt: String,
        @RequestParam("ownerId") ownerId: Int
    ): String {
        val holding = holdingsService.findById(holdingId)
        if (holding == null) {
            throw IllegalArgumentException("Holding not found: $holdingId")
        }
        val dividendAndPreferential = DividendAndPreferential(
            stock = holding.stock,
            dividendPrice = dividendPrice,
            fee = fee,
            transactionAt = LocalDate.parse(transactionAt)
        )
        dividendAndPreferentialService.save(dividendAndPreferential)
        return "redirect:/dividend-and-preferential/register?ownerId=$ownerId"
    }

    @GetMapping("/dividend-and-preferential")
    fun showDividendAndPreferentialList(model: Model): String {
        model.addAttribute("dividendAndPreferentials", dividendAndPreferentialService.findAll())
        return "dividend_and_preferential"
    }
}
