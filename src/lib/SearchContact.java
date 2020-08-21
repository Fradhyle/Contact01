package lib;

import java.util.ArrayList;
import java.util.HashMap;

//입력 받은 값으로 키 찾아서 키 리스트 반환
public class SearchContact extends ContactLib {
//	검색어 저장 변수 선언
	String searchWord = null;
	
//	생성자 및 입력값 대입
	public SearchContact(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public ArrayList<String> search() {
//		찾은 키를 저장할 ArrayList 선언
		ArrayList<String> keyList = new ArrayList<String>();
//		등록된 모든 연락처를 for문을 통해 순환 검색
		for (String currentKey : contacts.keySet()) {
//			현재 탐색을 시도중인 HashMap을 저장할 변수 선언
			HashMap<String, String> currentSearch = contacts.get(currentKey);
//			현재 탐색을 시도중인 HashMap 값을 String으로 변환
			String stringData = currentSearch.toString();
//			String으로 변환된 전체 연락처의 값에서 입력 받은 값 포함 여부 확인
			if (stringData.contains(searchWord)) {
//				검색어가 현재 탐색중인 HashMap 값에 포함되어 있으면 키값을 ArrayList에 저장
				keyList.add(currentKey);
			}
		}
//		연락처를 발견하지 못한 경우
		if (keyList.isEmpty()) {
			System.out.println("해당 검색어로 연락처를 찾을 수 없었습니다.");
			return keyList;
		}
//		연락처를 발견한 경우
		System.out.println("총 " + keyList.size() + "개의 연락처를 발견하였습니다.\n");
//		출력된 연락처의 순번 부여를 위한 변수 초기화
		int m = 1;
//		검색된 연락처 출력
		for (int n=0; n<keyList.size(); n++) {
			String key = keyList.get(n);
			HashMap<String, String> individualData = contacts.get(key);
			HashMapTidyUp tidyUp = new HashMapTidyUp(individualData);
			String[] arrayedData = tidyUp.tidyUp();
			System.out.println(m + ".");
			for(int i=0; i<arrayedData.length;i++) {
				System.out.println(arrayedData[i]);
			}
			System.out.println();
//			순번 증가
			m++;
		}
		return keyList;
	}
}
