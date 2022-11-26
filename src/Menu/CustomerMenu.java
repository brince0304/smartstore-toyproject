package Menu;

import Customer.Customer;
import Customer.Customers;
import Exceptions.ExceptionManager;
import Exceptions.SameCustomerIdException;

import java.io.IOException;

public class CustomerMenu implements Menu {

    static CustomerMenu customerMenu;
    private final Customers customers;


    private CustomerMenu(Customers customers) {
        this.customers = customers;
    }

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu(Customers.getInstance());
        }
        return customerMenu;
    }


    @Override
    public int showMenu() throws IOException {
        try {
            System.out.println("고객 관리 메뉴");
            System.out.println("1. 고객 추가");
            System.out.println("2. 고객 삭제");
            System.out.println("3. 고객 수정");
            System.out.println("4. 고객 명단 조회");
            System.out.println("5. 메인 메뉴로 돌아가기");
            System.out.println("메뉴를 선택하세요");
            return Integer.parseInt(inputString());
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
        return 0;
    }

    @Override
    public void selectMenu(int menu) throws IOException, SameCustomerIdException {
        try {
            switch (menu) {
                case 1:
                    System.out.println("등록을 원하는 인원 수를 입력하세요");
                    int count = Integer.parseInt(inputString());
                    for (int i = 0; i < count; i++) {
                        customerBuilder(customerBuilder());
                    }
                    break;
                case 2:
                    customers.printCustomerCountBySerialId();
                    deleteCustomerFromCustomersBySerialId(inputIdForCustomerDeleting());
                    break;
                case 3:
                    customers.printCustomerCountBySerialId();
                    System.out.println("수정을 원하는 고유 번호를 입력해주세요.");
                    updateCustomerInfoBySerialId(inputString());
                    break;
                case 4:
                    printCustomerListWithoutType();
                    break;
                case 5:
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    break;
            }
        }
        catch (NumberFormatException e){
            ExceptionManager.catchInputTypeMismatchException();
        }
    }


    private String inputIdForCustomerDeleting() throws IOException {
        System.out.println("삭제할 고객의 고유번호을 입력하세요");
        String serialId = inputString();
        return serialId;
    }


    public Customer customerBuilder() throws IOException, SameCustomerIdException {
        try {
            System.out.println("이름을 입력해주세요");
            String name = inputString();
            if (!validateCustomerName(name)) {
                return null;
            }
            System.out.println("아이디를 입력해주세요");
            String id = inputString();
            if (!validateCustomerId(id)) {
                return null;
            }
            if (checkIsCustomerExistBySerialId(id)) {
                ExceptionManager.catchSameCustomerIdException();
                return null;
            } else {
                System.out.println("이용 금액을 만원 단위로 입력해주세요");
                int spendMoney = Integer.parseInt(inputString());
                System.out.println("이용 시간을 시간 단위로 입력해주세요");
                int spendTime = Integer.parseInt(inputString());
                return Customer.of(name, id, spendMoney, spendTime);
            }
        }
        catch (NumberFormatException e) {
            ExceptionManager.catchInputTypeMismatchException();
            return null;
        }
    }






    public void customerBuilder(Customer customer) throws SameCustomerIdException {
        if (customer != null) {
            if (customers.addCustomer(customer)) {
                System.out.println("등록되었습니다.");
            } else {
                ExceptionManager.catchUnknownException();
            }
        }
    }

    public void deleteCustomerFromCustomersBySerialId(String id) throws SameCustomerIdException {
        if(!customers.isEmpty()){
        if(customers.getBySerialId(id)!=null) {
            customers.deleteCustomerBySerialId(id);
        }
        else{
            ExceptionManager.catchCustomerIdNotFoundedException();
        }}
        else{
            ExceptionManager.catchNoCustomersException();
        }

    }

    public void updateCustomerInfoBySerialId(String id) throws IOException,NumberFormatException {
        if(!customers.isEmpty()) {
            try {
                if (customers.getByCustomerId(id) == null) {
                } else {
                    System.out.println("고객 수정 메뉴");
                    System.out.println("이름을 입력해주세요");
                    String name = inputString();
                    if (!validateCustomerName(name)) {
                        return;
                    }
                    System.out.println("아이디를 입력해주세요");
                    String customerId = inputString();
                    if (!validateCustomerId(customerId)) {
                        return;
                    }
                    System.out.println("이용 금액을 입력해주세요");
                    int spendMoney = Integer.parseInt(inputString());
                    System.out.println("이용 시간을 입력해주세요");
                    int spendHour = Integer.parseInt(inputString());
                    customers.updateCustomerBySerialId(id, name, customerId, spendHour, spendMoney);
                }
            } catch (NumberFormatException e) {
                ExceptionManager.catchInputTypeMismatchException();
            }
        }
        else{
            ExceptionManager.catchNoCustomersException();
        }
    }


    public void printCustomerListWithoutType() {
        Customer[] list = customers.getCustomers();
        if (list != null) {
            if (list[0] == null) {
                ExceptionManager.catchNoCustomersException();
               return;
            }
            for (int i = 0; i < customers.getCustomerCount(); i++) {
                if (list[i] != null) {
                    System.out.println(list[i].toStringWithoutType());
                }
            }
        }
    }





    public boolean validateCustomerId(String customerId){
        if(customerId.length() < 5 || customerId.length() > 12){
            ExceptionManager.catchCustomerIdNotValidatedException();
            return false;
        }
        if(!customerId.matches("^[a-zA-Z0-9]*$")){
            ExceptionManager.catchCustomerIdNotValidatedException();
            return false;
        }
        return true;
    }

    public boolean validateCustomerName(String customerName){
        if(customerName.length() < 2){
            ExceptionManager.catchCustomerNameNotValidatedException();
            return false;
        }
        return true;
    }

    public boolean checkIsCustomerExistBySerialId(String customerId)  {
            for (int i = 0; i < customers.getCustomerCount(); i++) {
                if (customers.getCustomers()[i] != null) {
                    if (customers.getCustomers()[i].getCustomerId().equals(customerId)) {
                        ExceptionManager.catchSameCustomerIdException();
                        return true;
                    }
                }
            }
        return false;
    }



}
