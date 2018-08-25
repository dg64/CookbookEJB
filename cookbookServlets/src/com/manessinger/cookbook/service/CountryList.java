package com.manessinger.cookbook.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manessinger.cookbook.dto.CountryDump;

/**
 * Servlet implementation class CountryList
 */
@WebServlet(name="/CountryList", urlPatterns = { "/CountryList" })
public class CountryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB CookbookBean cbBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		processRequest(request, response);
	}
	
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CountryList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CountryList at "
                    + request.getContextPath() + "</h1>");
            List<CountryDump>  countries = cbBean.dumpCountries();
            if (countries.size() > 0) {
                out.println("<div class=\"cookbook-country-list\">");
                out.println("<dl>");
                for (CountryDump co : countries) {
                    out.println("<di>"+co.getName()+"</di>");
                    out.println("<dd>");
                    List<String> cities = co.getCities();
                    if (cities.size() > 0) {
                        out.println("<ul>");
                        for (String ci : cities) {
                            out.println("<li>"+ci+"</li>");
                        }
                        out.println("</ul>");
                    }
                    out.println("</dd>");
                }
                out.println("</dl>");
                out.println("</div>");            	
            }
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
