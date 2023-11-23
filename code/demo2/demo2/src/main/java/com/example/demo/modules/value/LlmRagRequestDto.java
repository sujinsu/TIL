//package com.example.demo.modules.value;
//
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.http.HttpMethod;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
///**
// * <pre>
// * Class Name : LlmRagRequestDto
// * Description : LLM RAG Request 정보 Dto
// *
// *  Modification Information
// *  Modify Date     Modifier        Comment
// *  -----------------------------------------------
// *  2023.11.22      Leesujin       Create
// *
// * </pre>
// *
// * @author Leesujin
// * @since 2023.11.22
// */
//@Getter
//@Setter
//public class LlmRagRequestDto {
//	private HttpMethod method;
//
////	private Map<String,String> headerMap; // 현재 필요하지 않음
//	private String uri;
//	private String queryString;
//	private String body;
////	private String userId; // body로 들어옴
//
////	private String loginIp;
////	private String referer;
//	public LlmRagRequestDto(String method){
//
//	}
//	public LlmRagRequestDto(HttpServletRequest request, String div) {
////		String CHAT_API_URI = "/api_v1/chat";
////		String RAG_DEMO_API_URI = "/api_v1/rag";
//
//		//22/12/14 kdh
//		//header정보 저장 추가
////		headerMap = new HashMap<>();
////		Enumeration<String> headerNames = request.getHeaderNames();
////
////		while(headerNames.hasMoreElements()){
////			String headerName = headerNames.nextElement();
////			String headerValue = request.getHeader(headerName);
////			headerMap.put(headerName, headerValue);
////		}
//
//		this.method = HttpMethod.resolve(request.getMethod());
//		this.uri = request.getRequestURI();
//		//22/12/13수정
//		//bard management URL삭제
////		if("chat".equals(div)){
////			this.uri = request.getRequestURI().replace(CHAT_API_URI, "");
////		}
////		else if("rag".equals(div) ||"ragfile".equals(div) ){
////		//bard API GATEWAY URL삭제
////			this.uri = request.getRequestURI().replace(RAG_DEMO_API_URI, "");
////		}
//
//		this.queryString = request.getQueryString();
//
//		try {
//			this.body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//
////			if("ragfile".equals(div)){
////				//json으로 쏘는걸로 바꾸는곳
////			}
////			else {
////				this.body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
////			}
//		} catch (IOException e) {
//			throw new InvalidLlmRagApiBodyException(e.getMessage());
//		}
//
////		this.loginIp = RemoteAddressFinder.getAddress(request);
////		this.referer = request.getHeader("referer");
//	}
//
//	/**
//	 * 기존 uri에 Query String을 붙인 uri 조회
//	 */
//	public String getUriWithQueryString() {
//		return this.uri + (StringUtils.hasText(this.queryString) ?  ("?" + this.queryString) : "");
//	}
//}
