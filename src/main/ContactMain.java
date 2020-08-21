package main;

import lib.ContactLib;
import lib.IntChecker;

public class ContactMain {	

	public static void main(String[] args) {
//		1차 라이브러리 적재
		ContactLib lib = new ContactLib();
//		메뉴 출력
		System.out.println("=======================\n"
						 + "다음 메뉴 중 하나를 선택하세요.\n"
						 + "=======================\n"
						 + "1. 회원 추가\n"
						 + "2. 회원 목록 보기\n"
						 + "3. 회원 정보 수정하기\n"
						 + "4. 회원 삭제\n"
						 + "5. 종료");		
//		메뉴 선택 입력 및 확인
		IntChecker intCheck = new IntChecker();
		int selection = intCheck.check();		
//		메뉴 이동
		switch(selection) {
//		연락처 추가
		case 1 :			
			lib.addContact();
			break;
//		연락처 목록 보기
		case 2 :
			lib.listContact();
			break;
//		연락처 수정
		case 3 :
			lib.modifyContact();
			break;
//		연락처 삭제
		case 4 :
			lib.deleteContact();
			break;
//		프로그램 종료
		case 5 :
			System.out.println("프로그램을 종료합니다.");
			return;
//		잘못 입력한 경우 메인 메뉴 다시 출력
		default :
			System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
			main(null);
		}
		return;
	}	
}