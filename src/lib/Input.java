package lib;

import java.util.Scanner;

// 입력값을 받고 올바른지 검증한 후 반환하는 클래스
public class Input extends ContactLib {
	Scanner scanner = new Scanner(System.in);
//	현재 시도중인 것이 추가인지(state = 1), 수정인지(state = 2) 확인하는 값을 저장하는 변수 선언
	int state = 0;
	
//	생성자
	public Input(int state) {
		this.state = state;
	}
	
//	이름 입력 메소드
	public String inputName() {
//		연락처 추가인 경우
		if (state == 1) {
			System.out.print("이름을 입력하세요: ");
			String name = scanner.next();
			return name;
//		연락처 수정인 경우
		} else if (state == 2) {
			System.out.print("이름을 수정합니다."
						   + "새로운 이름을 입력하세요: ");
			String name = scanner.next();
			return name;
		}
		return null;
	}
	
//	전화번호 입력 메소드
	public String inputNumber() {
//		연락처 추가인 경우
		if (state == 1) {
//			올바른 전화번호를 입력할 때까지 반복
			while(true) {
//				전화번호 입력
				System.out.print("전화번호를 입력하세요('-' 기호 제외): ");
				String number = scanner.next();
//				입력한 값을 char 배열로 변환
				char[] numchar = number.toCharArray();
//				숫자가 아닌 값이 몇개인지 저장할 변수 선언
				int count = 0;
//				char 배열을 1글자씩 대입하여 숫자인지 검증
				for (char currentChar : numchar) {
					if (!Character.isDigit(currentChar)) {
						count++;
					}
				}
//				숫자가 아닌 글자 갯수가 1개라도 있을 경우
				if (count >= 1) {
					System.out.println("전화번호가 아닙니다. 다시 입력하세요.");
					continue;
				}			
//				전화번호 중복 여부 검증
				if (contacts.containsKey(number)) {
					System.out.println("이미 입력된 전화번호입니다. 다시 입력하세요.");
					continue;
				}
//				전화번호 자릿수 검증(유선 전화 서울 지역번호+3자리 국번인 경우와 050X+4자리 국번인 경우까지 허용)
				if (number.length() < 9 || number.length() > 12) {
					System.out.println("전화번호를 잘못 입력하였습니다. 다시 입력하세요.");
					continue;
				}
				return number;
			}
//		연락처 수정인 경우
		} else if (state == 2) {
			System.out.println("전화번호를 수정합니다.");
//			올바른 전화번호를 입력할 때까지 반복
			while(true) {
//				전화번호 입력
				System.out.print("전화번호를 입력하세요('-' 기호 제외): ");
				String number = scanner.next();
//				입력한 값을 char 배열로 변환
				char[] numchar = number.toCharArray();
//				숫자가 아닌 값이 몇개인지 저장할 변수 선언
				int count = 0;
//				char 배열을 1글자씩 대입하여 숫자인지 검증
				for (char currentChar : numchar) {
					if (!Character.isDigit(currentChar)) {
						count++;
					}
				}
//				숫자가 아닌 글자 갯수가 1개라도 있을 경우
				if (count >= 1) {
					System.out.println("전화번호가 아닙니다. 다시 입력하세요.");
					continue;
				}
//				전화번호 중복 여부 검증
				if (contacts.containsKey(number)) {
					System.out.println("이미 입력된 전화번호입니다. 다시 입력하세요.");
					continue;
				}
//				전화번호 자릿수 검증(유선 전화 서울 지역번호+3자리 국번인 경우와 050X+4자리 국번인 경우까지 허용)
				if (number.length() < 9 || number.length() > 12) {
					System.out.println("전화번호를 잘못 입력하였습니다. 다시 입력하세요.");
					continue;
				}
				return number;
			}			
		}
		return null;
	}
	
//	구분 입력 메소드
	public String inputGroup() {
//		연락처 추가인 경우
		if (state == 1) {
//			올바른 구분을 입력할 때까지 반복
			while(true) {
				System.out.print("\"가족\", \"친구\", \"회사\", \"기타\" 중 하나를 입력하세요.: ");
				String group = scanner.next();
//				올바른 구분 입력 여부 검증
				if (group.equals("가족")
				 || group.equals("친구")
				 || group.equals("회사")
				 || group.equals("기타")) {
					return group;
//				잘못 입력한 경우
				} else {
					System.out.println("구분을 잘못 입력하셨습니다.");
					continue;
				}
			}
//		연락처 수정인 경우
		} else if (state == 2) {
//			올바른 구분을 입력할 때까지 반복
			while(true) {
				System.out.print("구분을 수정합니다.\n"
							   + "\"가족\", \"친구\", \"회사\", \"기타\" 중 하나를 입력하세요.: ");
				String group = scanner.next();
//				올바른 구분 입력 여부 검증
				if (group.equals("가족")
				 || group.equals("친구")
				 || group.equals("회사")
				 || group.equals("기타")) {
					return group;
//				잘못 입력한 경우
				} else {
					System.out.println("구분을 잘못 입력하셨습니다.");
					continue;
				}
			}
		}
		return null;
	}
}
