package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import cn.edu.hnuc.volunteer_Sys.entity.Activity;
import cn.edu.hnuc.volunteer_Sys.util.info_Query;

/**
 *
 * @author zzliux
 */
@WebServlet("/index_show")
public class IndexInfo extends HttpServlet {

    public IndexInfo(){
        super();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        doPost(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        StringBuilder sb = new StringBuilder();
        ArrayList<Activity> acts = info_Query.actsQuery();
        sb.append( (new Gson()).toJson(acts));
        PrintWriter pw = res.getWriter();
        pw.print(sb.toString());
        pw.close();
    }
}
