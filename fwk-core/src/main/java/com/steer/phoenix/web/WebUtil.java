package com.steer.phoenix.web;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class WebUtil {
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String CONTENTTYPE_DEFAULT = "application/x-www-form-urlencoded";
    public static final String CONTENTTYPE_JSON = "application/json";
    public static final String CONTENTTYPE_JSON_RPC = "application/json-rpc";
    public static final String X_Requested_With = "X-Requested-With";
    public static final String X_Requested_With_AJAX = "XMLHttpRequest";
    public static final String USER_AGENT = "User-Agent";
    public static final String WECHART = "MicroMessenger";
    public static final String ALIPAY = "ApliPayClient";
    public static final String UNKNOWN = "unknown";

    public static boolean isAjax(HttpServletRequest req) {
        return "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
    }

    public static HttpServletRequest getCurrentRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static boolean isJson(String contentType) {
        return "application/json".equalsIgnoreCase(contentType);
    }

    public static boolean isJsonRpc(String contentType) {
        return "application/json-rpc".equalsIgnoreCase(contentType);
    }

    public static boolean isJsonResponse(HttpServletRequest request, HandlerMethod handlerMethod) {
        if (handlerMethod != null) {
            ResponseBody responseBody = (ResponseBody) handlerMethod.getMethodAnnotation(ResponseBody.class);
            if (responseBody != null) {
                return true;
            }

            RestController restAnnotation = (RestController) handlerMethod.getBean().getClass()
                    .getAnnotation(RestController.class);
            if (restAnnotation != null) {
                return true;
            }
        }

        return false;
    }

    public static String getContentType(HttpServletRequest req) {
        String contentType = req.getHeader("content-type");
        int iSemicolonIdx;
        if (contentType != null && (iSemicolonIdx = contentType.indexOf(";")) != -1) {
            contentType = contentType.substring(0, iSemicolonIdx);
        }

        return contentType;
    }

    public static void setContentType(HttpServletResponse resp, String contentType) {
        resp.setContentType(contentType + ";charset=" + "UTF-8");
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }

        return ip;
    }

//	public static boolean isWechart(HttpServletRequest request) {
//		String heads = request.getHeader("User-Agent");
//		return Strings.isBlank(heads)
//				? false
//				: heads.indexOf("MicroMessenger") != -1 || !Strings.isBlank(request.getParameter("MicroMessenger"))
//						|| !Strings.isBlank((String) request.getAttribute("MicroMessenger"));
//	}

    public static void setWechartTag(HttpServletRequest request) {
        request.setAttribute("MicroMessenger", "1");
    }

//	public static boolean isApliPay(HttpServletRequest request) {
//		String heads = request.getHeader("User-Agent");
//		return Strings.isBlank(heads)
//				? false
//				: heads.indexOf("ApliPayClient") != -1 || !Strings.isBlank(request.getParameter("ApliPayClient"))
//						|| !Strings.isBlank((String) request.getAttribute("ApliPayClient"));
//	}

//	public static void setAlipayTag(HttpServletRequest request) {
//		request.setAttribute("ApliPayClient", "1");
//	}

//	public static void dealWerchartAlipayTag(HttpServletRequest request) {
//		if (isWechart(request)) {
//			setWechartTag(request);
//		} else if (isApliPay(request)) {
//			setAlipayTag(request);
//		}
//
//	}

//	public static String parseServerFromUrl(String url) {
//		if (!Strings.isBlank(url) && url.startsWith("http")) {
//			String tmp = url.startsWith("https")
//					? url.substring("https://".length())
//					: url.substring("http://".length());
//			int idx = tmp.indexOf(58);
//			if (idx > 0) {
//				return tmp.substring(0, idx);
//			} else {
//				int idx2 = tmp.indexOf(47);
//				return idx2 > 0 ? tmp.substring(0, idx2) : tmp;
//			}
//		} else {
//			return null;
//		}
//	}

    public static String urlAppend(String url, String perameterName, String perameterValue)
            throws UnsupportedEncodingException {
        if (url.indexOf("?") == -1) {
            url = url + "?";
        } else {
            url = url + "&";
        }

        return url + perameterName + "=" + URLEncoder.encode(perameterValue, "UTF-8");
    }

//	public static String getRequestUriWithParameters(HttpServletRequest request) {
//		Map map = request.getParameterMap();
//		StringBuffer sb = new StringBuffer();
//		sb.append("?");
//		if (map != null && !map.isEmpty()) {
//			Iterator arg3 = map.keySet().iterator();
//
//			while (arg3.hasNext()) {
//				Object param = arg3.next();
//				String pn = (String) param;
//				String value = getParameterValue(map.get(pn));
//				sb.append(pn).append("=").append("password".equals(pn) ? "******" : value).append("&");
//			}
//		}
//
//		sb.deleteCharAt(sb.length() - 1);
//		return request.getRequestURI() + sb.toString();
//	}

//	private static String getParameterValue(Object obj) {
//		return obj == null
//				? ""
//				: (obj instanceof String
//						? (String) obj
//						: (obj instanceof String[] ? ((String[]) obj)[0] : obj.toString()));
//	}

//	public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
//		if (request == null) {
//			return null;
//		} else {
//			Enumeration paramNames = request.getParameterNames();
//			TreeMap params = new TreeMap();
//			String paramPrefix = prefix == null ? "" : prefix;
//
//			while (paramNames != null && paramNames.hasMoreElements()) {
//				String paramName = (String) paramNames.nextElement();
//				if ("".equals(paramPrefix) || paramName.startsWith(paramPrefix)) {
//					String unprefixed = paramName.substring(paramPrefix.length());
//					String[] values = request.getParameterValues(paramName);
//					if (values != null && values.length != 0) {
//						if (values.length > 1) {
//							params.put(unprefixed, values);
//						} else {
//							params.put(unprefixed, values[0]);
//						}
//					}
//				}
//			}
//
//			return params;
//		}
//	}

//    public static String getContextPath(HttpServletRequest request) {
//        return org.apache.shiro.web.util.WebUtils.getContextPath(request);
//    }

    /**
     * 获取操作系统,浏览器及浏览器版本信息
     * @param request
     * @return
     */
    public static String getOsAndBrowserInfo(HttpServletRequest request){
        String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String os = "";
        String browser = "";

        //=================OS Info=======================
        if (userAgent.toLowerCase().indexOf("windows") >= 0 )
        {
            os = "Windows";
        } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().indexOf("android") >= 0)
        {
            os = "Android";
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }
        //===============Browser===========================
        if (user.contains("edge"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
                    + "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera")){
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
                        +"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            }else if(user.contains("opr")){
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
                        .replace("OPR", "Opera");
            }

        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  ||
                (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) ||
                (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser="IE" + IEVersion.substring(0,IEVersion.length() - 1);
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }

        return os +" --- "+ browser ;
    }
}
