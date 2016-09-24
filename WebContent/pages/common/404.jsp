<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>
<%@ include file="/pages/common/commonJS.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404 - 页面不存在</title>
</head>
<body class="light_bg2">
<div class="errCot">
        <div class="errTit">
          <table border="0">
            <tr>
              <td class="errIcon404"></td>
              <td class="errTitTxt">Error 404</td>
            </tr>
          </table>
        </div>
        
        <div class="centLine"></div>
        
        <div class="detailCon">
          <div class="explainTxt">您要求访问的页面不存在！</div>
          <div class="detailTxt">
             <ul>
               <li>系统找不到您所请求的文件或脚本。请检查URL 以确保路径正确。</li>
               <li>如果问题依然存在，请与系统管理员联系。</li>
             </ul>
          </div>
           <p class="btn_box"><input  class="btn_back"  type="button"  onclick="javascript:history.back(-1);" /></p>
        </div>
       
    </div>
    </body>
</html>