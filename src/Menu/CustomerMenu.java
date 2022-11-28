package Menu;

import Customer.Customer;
import Customer.Customers;
import Utils.*;

import java.io.IOException;

public class CustomerMenu implements Menu { //예외는 메뉴에서 처리

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
    public void selectMenu(int menu) throws IOException {
        try {
            switch (menu) {
                case 1:
                    System.out.println("등록을 원하는 인원 수를 입력하세요");
                    int count = Integer.parseInt(inputString());
                    repeatCustomerRegisterByCount(count);
                    break;
                case 2:
                    if(!customers.isEmpty()){
                    customers.printCustomerCountBySerialId();
                    deleteCustomerFromCustomersBySerialId(inputIdForCustomerDeleting());}
                    else{
                        PrintNoCustomer.print();
                    }
                    break;
                case 3:
                    if(!customers.isEmpty()) {
                        customers.printCustomerCountBySerialId();
                        System.out.println("수정을 원하는 고유 번호를 입력해주세요.");
                        updateCustomerInfoBySerialId(inputString());
                    }
                    else{
                        PrintNoCustomer.print();
                    }
                    break;
                case 4:
                    if(!customers.isEmpty()) {
                        printCustomerListWithoutType();
                    }
                    else{
                        PrintNoCustomer.print();
                    }
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


    public String inputIdForCustomerDeleting() throws IOException {
        System.out.println("삭제할 고객의 고유번호을 입력하세요");
        String serialId = inputString();
        return serialId;
    }

    public void repeatCustomerRegisterByCount(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            while(true) {
                System.out.println(i + 1 + "번째 고객 정보를 입력하세요");
                Customer customer = buildCustomer();
                if(customer!=null){
                    addCustomerToCustomers(customer);
                    break;
                }
                else{
                    System.out.println("다시 시도해주세요.");
                    break;
                }
            }
        }
    }


    public Customer buildCustomer() throws IOException {
        try {
            System.out.println("고객 이름을 영문자로 3글자 이상 입력해주세요.");
            String name = inputString();
            if (!validateCustomerName(name)) {
                return null;
            }
            System.out.println("고객아이디를 5자이상 12자 이하로 입력해주세요.");
            System.out.println("첫글자에는 영문자만 입력 가능합니다. 이후엔 대소문자/숫자/특수문자(_)만 입력 가능합니다.");
            String id = inputString();
            if (!validateCustomerId(id)) {
                return null;
            }
            if (checkIsCustomerExistBySerialId(id)) {
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






    public void addCustomerToCustomers(Customer customer)  {
        if (customer != null) {
            if (customers.addCustomer(customer)) {
                System.out.println("등록되었습니다.");
            } else {
                System.out.println("등록에 실패했습니다.");
            }
        }
    }

    public void deleteCustomerFromCustomersBySerialId(String id) {
        if(!customers.isEmpty()){
        if(customers.getBySerialId(id)!=null) {
            customers.deleteCustomerBySerialId(id);
        }
        else{
            PrintCustomerIdNotFound.print();
        }}
        else{
            PrintNoCustomer.print();
        }

    }

    public void updateCustomerInfoBySerialId(String id) throws IOException,NumberFormatException {
        if(!customers.isEmpty()) {
            try {
                if (customers.getByCustomerId(id) == null) {
                    PrintCustomerIdNotFound.print();
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
                    System.out.println("수정되었습니다.");
                }
            } catch (NumberFormatException e) {
                ExceptionManager.catchInputTypeMismatchException();
            }
        }
        else{
            PrintNoCustomer.print();
        }
    }


    public void printCustomerListWithoutType() {
        Customer[] list = customers.getCustomers();
        if (list != null) {
            if (list[0] == null) {
                PrintNoCustomer.print();
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
        if(Validator.validCustomerId(customerId)){
            return true;
        }
        else{
            PrintCustomerIdNotValidated.print();
            return false;
        }
    }

    public boolean validateCustomerName(String customerName){
        if(Validator.validCustomerName(customerName)){
           return true;
        }
        else{
            PrintCustomerNameNotValidated.print();
            return false;
        }
    }

    public boolean checkIsCustomerExistBySerialId(String customerId)  {
            for (int i = 0; i < customers.getCustomerCount(); i++) {
                if (customers.getCustomers()[i] != null) {
                    if (customers.getCustomers()[i].getCustomerId().equals(customerId)) {
                        PrintSameCustomerId.print();
                        return true;
                    }
                }
            }
        return false;
    }



}
