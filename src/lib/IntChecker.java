package lib;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import main.ContactMain;

// Integer 값을 받을 때 올바른 값을 입력하였는지 확인
public class IntChecker {
//	Scanner 선언
	Scanner scanner = new Scanner(System.in);
//	입력값을 저장할 변수 선언
	int selection = 0;
	
//	생성자
	public IntChecker () { }

	public int check() {
		
		while(true) {
			try {
				selection = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("잘못 입력하였습니다. 선택값을 숫자로 입력해주세요.");
				continue;
			} catch (NoSuchElementException e) {
				System.out.println("오류가 발생하였습니다. 메인 메뉴로 복귀합니다.");
				ContactMain.main(null);
			} catch (IllegalStateException e) {
				System.out.println("오류가 발생하였습니다. 메인 메뉴로 복귀합니다.");
				ContactMain.main(null);
			}
			return selection;
		}
	}
}
