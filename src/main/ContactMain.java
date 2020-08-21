package main;

import lib.ContactLib;
import lib.IntChecker;

public class ContactMain {	

	public static void main(String[] args) {
//		1�� ���̺귯�� ����
		ContactLib lib = new ContactLib();
//		�޴� ���
		System.out.println("=======================\n"
						 + "���� �޴� �� �ϳ��� �����ϼ���.\n"
						 + "=======================\n"
						 + "1. ȸ�� �߰�\n"
						 + "2. ȸ�� ��� ����\n"
						 + "3. ȸ�� ���� �����ϱ�\n"
						 + "4. ȸ�� ����\n"
						 + "5. ����");		
//		�޴� ���� �Է� �� Ȯ��
		IntChecker intCheck = new IntChecker();
		int selection = intCheck.check();		
//		�޴� �̵�
		switch(selection) {
//		����ó �߰�
		case 1 :			
			lib.addContact();
			break;
//		����ó ��� ����
		case 2 :
			lib.listContact();
			break;
//		����ó ����
		case 3 :
			lib.modifyContact();
			break;
//		����ó ����
		case 4 :
			lib.deleteContact();
			break;
//		���α׷� ����
		case 5 :
			System.out.println("���α׷��� �����մϴ�.");
			return;
//		�߸� �Է��� ��� ���� �޴� �ٽ� ���
		default :
			System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");
			main(null);
		}
		return;
	}	
}