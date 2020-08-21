package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import main.ContactMain;

public class ContactLib {
//	해시맵 구조 선언
	public static HashMap<String, HashMap<String, String>> contacts =
			new HashMap<String, HashMap<String, String>>();
	Scanner scanner = new Scanner(System.in);
	
//	생성자
	public ContactLib() { };
	
//	연락처 추가
	public void addContact() {		
//		입력 클래스 및 이름 입력 메소드 호출
		Input inputName = new Input(1);
		String name = inputName.inputName();		
//		입력 클래스 및 전화번호 입력 메소드 호출
		Input inputNumber = new Input(1);
		String number = inputNumber.inputNumber();		
//		입력 클래스 및 구분 입력 메소드 호출
		Input inputGroup = new Input(1);
		String group = inputGroup.inputGroup();		
//		개인에 대한 정보를 저장할 HashMap 선언
		HashMap<String, String> individualData = new HashMap<String, String>();		
//		개인 정보 해시맵에 입력받은 정보 입력
		individualData.put("이름", name);
		individualData.put("전화번호", number);
		individualData.put("구분", group);		
//		전체 연락처 해시맵에 입력
		contacts.put(number, individualData);
		System.out.println("연락처를 저장하였습니다.\n");		
//		추가 입력 여부 질문
		while(true) {
			System.out.println("연락처를 더 입력하시겠습니까?\n"
							 + "1. 예\n"
							 + "2. 아니오");
//			메뉴 선택 입력 및 확인
			IntChecker intCheck = new IntChecker();
			int selection = intCheck.check();
//			선택에 따른 메뉴 이동
			if (selection == 1) {
				this.addContact();
				break;
			} else if (selection == 2) {
//				메인 메뉴 복귀
				System.out.println("메인 메뉴로 복귀합니다.\n");
				ContactMain.main(null);
				return;
			} else {
				System.out.println("잘못 입력하였습니다. 다시 선택하세요.");
				continue;
			}
		}
	}
	
//	전체 연락처 출력
	public void listContact() {
//		등록된 연락처 갯수 출력
		Set<String> keys = contacts.keySet();
		Iterator<String> it = keys.iterator();
		int count = 0;
		while(it.hasNext()) {
			if(it.next() != null) {
				count++;
			}
		}
		System.out.println("총 " + count + "개의 연락처가 있습니다.\n");
//		출력된 연락처의 순번 부여를 위한 변수 초기화
		int m = 1;
//		등록된 연락처 모두 출력
		for(String key : contacts.keySet()) {
			HashMap<String, String> individualData = contacts.get(key);
//			HashMap을 보기 좋게 정리하는 클래스 및 메소드 호출
			HashMapTidyUp tidyUp = new HashMapTidyUp(individualData);
			String[] arrayedData = tidyUp.tidyUp();
//			순번 출력
			System.out.println(m + ".");
//			연락처 출력
			for(int i=0; i<arrayedData.length;i++) {
				System.out.println(arrayedData[i]);
			}
//			연락처 1개마다 한줄 띄기
			System.out.println();
//			순번 증가
			m++;
		}
//		메인 메뉴 복귀
		System.out.println("메인 메뉴로 복귀합니다.\n");
		ContactMain.main(null);
		return;
	}
	
//	등록된 연락처 수정
	public void modifyContact() {
		System.out.print("수정할 연락처의 이름을 입력하세요: ");
//		검색할 단어 입력
		String searchWord = scanner.next();
//		연락처 검색 클래스 및 검색 메소드 호출
		SearchContact search = new SearchContact(searchWord);
		ArrayList<String> keyList = search.search();
//		검색 결과가 없는 경우
		if (keyList.size() == 0) {
//			재검색 선택 질문
			while(true) {
				System.out.println("다시 검색하시겠습니까?\n"
								 + "1. 예\n"
								 + "2. 아니오");
//				메뉴 선택 입력 및 확인
				IntChecker intCheck = new IntChecker();
				int selection = intCheck.check();				
//				선택에 따른 메뉴 이동
				if (selection == 1) {
					this.modifyContact();
					break;
				} else if (selection == 2) {
//					메인 메뉴 복귀
					System.out.println("\n메인 메뉴로 복귀합니다.\n");
					ContactMain.main(null);
					return;
				} else {
					continue;
				}
			}
//		검색 결과가 1개인 경우
		} else if (keyList.size() == 1) {
			String key = keyList.get(0);
			HashMap<String, String> individualData = contacts.get(key);
			while(true) {
//				수정할 정보 선택
				System.out.println("어떤 정보를 수정하시겠습니까?\n"
								 + "1. 이름\n"
								 + "2. 전화번호\n"
								 + "3. 구분");
//				메뉴 선택 입력 및 확인
				IntChecker intCheck = new IntChecker();
				int selection = intCheck.check();
//				이름 수정을 선택한 경우
				if (selection == 1) {
					String name = null;
					Input inputName = new Input(2);
					name = inputName.inputName();
					individualData.put("이름", name);
					contacts.put(key, individualData);
//				전화번호 수정을 선택한 경우
				} else if (selection == 2) {
//					전화번호 입력 메소드 호출
					String number = null;
					Input inputNumber = new Input(2);
					number = inputNumber.inputNumber();
					individualData.put("전화번호", number);
					contacts.put(number, individualData);
					contacts.remove(key);
					key = number;
//				구분 수정을 선택한 경우
				} else if (selection == 3) {
//					구분 입력 메소드 호출
					String group = null;
					Input inputGroup = new Input(2);
					group = inputGroup.inputGroup();
					individualData.put("구분", group);
					contacts.put(key, individualData);
				} else {
					System.out.println("잘못 입력하였습니다. 다시 선택하세요.");
					continue;
				}
				break;
			}
//			수정 결과 출력
			System.out.println("다음과 같이 변경되었습니다.\n");
			HashMap<String, String> modifiedData = contacts.get(key);
//			HashMap을 보기 좋게 정리하는 클래스 및 메소드 호출
			HashMapTidyUp modifiedTidyUp = new HashMapTidyUp(modifiedData);
			String[] modifiedArrayedData = modifiedTidyUp.tidyUp();
//			수정된 연락처 출력
			for(int i=0; i<modifiedArrayedData.length;i++) {
				System.out.println(modifiedArrayedData[i]);
			}
//			메인 메뉴 복귀
			System.out.println("메인 메뉴로 복귀합니다.\n");
			ContactMain.main(null);
			return;
		} else {
			System.out.print("어떤 연락처를 수정하시겠습니까? ");
//			메뉴 선택 입력 및 확인
			IntChecker intCheck = new IntChecker();
//			올바른 ArrayList index 값 지정을 위해 -1 처리
			int selection = 0;
//			선택한 순번에 해당하는 키 반환하고 없는 숫자를 입력했을 경우 재시도
			String key = null;
			while(true) {
				try {
					selection = intCheck.check();
					selection = selection - 1;
					key = keyList.get(selection);
//				ArrayList 없는 범위 안의 값을 입력한 경우 예외 처리
				} catch (IndexOutOfBoundsException e) {
					System.out.print("잘못 입력하셨습니다. 다시 입력하세요.:");
					continue;
				}
				break;
			}
			HashMap<String, String> individualData = contacts.get(key);
//			HashMap을 보기 좋게 정리하는 클래스 및 메소드 호출
			HashMapTidyUp tidyUp = new HashMapTidyUp(individualData);
			String[] arrayedData = tidyUp.tidyUp();
//			선택된 연락처 출력
			for(int i=0; i<arrayedData.length;i++) {
				System.out.println(arrayedData[i]);
			}
//			수정할 연락처 선택 확인
			while(true) {
				System.out.println("이 연락처를 수정하시겠습니까?\n"
								 + "1. 예\n"
								 + "2. 아니오");
//				메뉴 선택 입력 및 확인
				selection = intCheck.check();
//				선택에 따른 메뉴 이동
//				수정하기로 한 경우
				if (selection == 1) {
					while(true) {
						System.out.println("어떤 정보를 수정하시겠습니까?\n"
										 + "1. 이름\n"
										 + "2. 전화번호\n"
										 + "3. 구분");
						selection = intCheck.check();
//						이름 수정을 선택한 경우
						if (selection == 1) {
							String name = null;
							Input inputName = new Input(2);
							name = inputName.inputName();
							individualData.put("이름", name);
							contacts.put(key, individualData);
//						전화번호 수정을 선택한 경우
						} else if (selection == 2) {
							String number = null;
							Input inputNumber = new Input(2);
							number = inputNumber.inputNumber();
							individualData.put("전화번호", number);
							contacts.put(number, individualData);
							contacts.remove(key);
							key = number;
//						구분 수정을 선택한 경우
						} else if (selection == 3) {
							String group = null;
							Input inputGroup = new Input(2);
							group = inputGroup.inputGroup();
							individualData.put("구분", group);
							contacts.put(key, individualData);
//						메뉴 선택을 잘못한 경우
						} else {
							System.out.println("잘못 입력하였습니다. 다시 선택하세요.");
							continue;
						}
						break;
					}
//					변경 후 결과값 출력
					System.out.println("다음과 같이 변경되었습니다.\n");
					HashMap<String, String> modifiedData = contacts.get(key);
//					HashMap을 보기 좋게 정리하는 클래스 및 메소드 호출
					HashMapTidyUp modifiedTidyUp = new HashMapTidyUp(modifiedData);
					String[] modifiedArrayedData = modifiedTidyUp.tidyUp();
//					수정된 연락처 출력
					for(int i=0; i<modifiedArrayedData.length;i++) {
						System.out.println(modifiedArrayedData[i]);
					}
//					메인 메뉴 복귀
					System.out.println("메인 메뉴로 복귀합니다.\n");
					ContactMain.main(null);
					return;
//				수정하지 않기로 한 경우
				} else if (selection == 2) {
//					결과 출력시 대상 연락처 이름을 저장할 변수 선언
					String targetName = individualData.get("이름");
//					메인 메뉴 복귀
					System.out.println(targetName + "(" + key + ")" + "의 연락처를 수정하지 않았습니다.\n"
									 + "메인 메뉴로 복귀합니다.");
//					메인 메뉴 복귀
					ContactMain.main(null);
					return;
//				메뉴 선택을 잘못한 경우
				} else {
					System.out.println("잘못 입력하였습니다. 다시 선택하세요.");
					continue;
				}
			}
		}
	}
	
//	등록된 연락처 삭제
	public void deleteContact() {
		System.out.print("삭제할 연락처의 이름을 입력하세요: ");
		String searchWord = scanner.next();
		SearchContact search = new SearchContact(searchWord);
		ArrayList<String> keyList = search.search();
//		검색 결과가 없는 경우
		if (keyList.size() == 0) {
//			재검색 선택 질문
			while(true) {
				System.out.println("다시 검색하시겠습니까?\n"
								 + "1. 예\n"
								 + "2. 아니오");
//				메뉴 선택 입력 및 확인
				IntChecker intCheck = new IntChecker();
				int selection = intCheck.check();
//				선택에 따른 메뉴 이동
				if (selection == 1) {
					deleteContact();
					break;
				} else if (selection == 2) {
//					메인 메뉴 복귀
					System.out.println("\n메인 메뉴로 복귀합니다.\n");
					ContactMain.main(null);
					break;
				} else {
					System.out.println("잘못 입력하였습니다. 다시 선택하세요.");
					continue;
				}
			}
//		검색 결과가 1개인 경우
		} else if (keyList.size() == 1) {
//			검색된 연락처의 키값 저장
			String key = keyList.get(0);
//			삭제 확인
			while(true) {
				System.out.println("정말 삭제하시겠습니까?\n"
								 + "1. 예\n"
								 + "2. 아니오");
//				결과 출력시 대상 연락처 이름을 저장할 변수 선언
				HashMap<String, String> individualData = contacts.get(key);
				String targetName = individualData.get("이름");
//				메뉴 선택 입력 및 확인
				IntChecker intCheck = new IntChecker();
				int selection = intCheck.check();
//				선택에 따른 메뉴 이동
//				삭제를 선택한 경우
				if (selection == 1) {
					contacts.remove(key);
					System.out.println(targetName + "(" + key + ")" + "의 연락처가 삭제되었습니다.\n"
									 + "메인 메뉴로 복귀합니다.\n");
//					메인 메뉴 복귀
					ContactMain.main(null);
					return;
//				삭제하지 않기로 한 경우
				} else if (selection == 2) {
//					메인 메뉴 복귀
					System.out.println(targetName + "(" + key + ")" + "의 연락처를 삭제하지 않았습니다.\n"
									 + "메인 메뉴로 복귀합니다.\n");
					ContactMain.main(null);
					return;
//				잘못 입력한 경우
				} else {
					System.out.println("잘못 입력하였습니다. 다시 선택하세요.\n");
					continue;
				}
			}
//		검색된 연락처가 2개 이상인 경우
		} else {
			System.out.print("어떤 연락처를 삭제하시겠습니까? ");
//			메뉴 선택 입력 및 확인
			IntChecker intCheck = new IntChecker();
//			올바른 ArrayList index 값 지정을 위해 -1 처리
			int selection = 0;
//			선택한 순번에 해당하는 키 반환하고 없는 숫자를 입력했을 경우 재시도
			String key = null;
			while(true) {
				try {
					selection = intCheck.check();
					selection = selection - 1;
					key = keyList.get(selection);
//				ArrayList 없는 범위 안의 값을 입력한 경우 예외 처리
				} catch (IndexOutOfBoundsException e) {
					System.out.print("잘못 입력하셨습니다. 다시 입력하세요.:");
					continue;
				}
				break;
			}
			HashMap<String, String> individualData = contacts.get(key);
//			HashMap을 보기 좋게 정리하는 클래스 및 메소드 호출
			HashMapTidyUp tidyUp = new HashMapTidyUp(individualData);
			String[] arrayedData = tidyUp.tidyUp();
//			선택한 연락처 출력
			for(int i=0; i<arrayedData.length;i++) {
				System.out.println(arrayedData[i]);
			}
//			선택한 연락처 출력 후 한줄 띄기
			System.out.println();
//			삭제 확인
			while(true) {
				System.out.println("이 연락처를 삭제하시겠습니까?\n"
								 + "1. 예\n"
								 + "2. 아니오");
//				결과 출력시 대상 연락처 이름을 저장할 변수 선언
				String targetName = individualData.get("이름");
//				메뉴 선택 입력 및 확인
				selection = intCheck.check();
//				선택에 따른 메뉴 이동
//				삭제하기로 한 경우
				if (selection == 1) {
					contacts.remove(key);
					System.out.println(targetName + "(" + key + ")" + "의 연락처가 삭제되었습니다.\n"
									 + "메인 메뉴로 복귀합니다.\n");
					ContactMain.main(null);
					return;
//				삭제하지 않기로 한 경우
				} else if (selection == 2) {
//					메인 메뉴 복귀
					System.out.println(targetName + "(" + key + ")" + "의 연락처를 삭제하지 않았습니다.\n"
									 + "메인 메뉴로 복귀합니다.\n");
					ContactMain.main(null);
					return;
				} else {
					System.out.println("잘못 입력하였습니다. 다시 선택하세요.");
					continue;
				}
			}
		}
	}
}