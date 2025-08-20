package com.example.stock.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest

@Controller
class CustomErrorController : ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

        if (status != null) {
            val statusCode = Integer.valueOf(status.toString())

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/"
            }
        }
        return "redirect:/" // Not Found 以外もトップページにリダイレクト
    }
}
