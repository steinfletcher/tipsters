package io.tipsters.filter

import org.springframework.stereotype.Component

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

@Component
class CorsFilter : Filter {

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val request = req as HttpServletRequest
        val response = res as HttpServletResponse
        val requestOrigin = request.getHeader("Origin")
        response.setHeader("Access-Control-Allow-Origin", requestOrigin)
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "GET")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, X-Requested-With")

        chain.doFilter(req, res)
    }

    override fun destroy() {
    }
}
