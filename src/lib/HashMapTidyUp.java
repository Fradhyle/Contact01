package lib;

import java.util.HashMap;

//개인 연락처 HashMap을 String으로 변환하여 보기 좋게 수정
public class HashMapTidyUp {
//	변환 대상 HashMap 변수 선언
	HashMap<String, String> individualData = null;
	String key = null;
	
//	생성자 및 입력값 대입
	public HashMapTidyUp(HashMap<String, String> individualData) {
		this.individualData = individualData;
	}
	
//	변환 메소드
	public String[] tidyUp() {
//		입력받은 HashMap 값을 String으로 변환
		String stringData = individualData.toString();
//		변환된 String에서 중괄호 삭제
		String unbracketedData = stringData.replace("{", "");
		unbracketedData = unbracketedData.replace("}", "");
//		"="를 ": "으로 치환
		String replacedData = unbracketedData.replace("=", ": ");
//		변환 결과를 , 기준으로 나눠서 String 배열에 저장
		String[] arrayedData = replacedData.split(", ");
//		String 배열 반환
		return arrayedData;
	}
}
