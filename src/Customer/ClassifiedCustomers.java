package Customer;

import java.util.Arrays;
import java.util.Comparator;

public class ClassifiedCustomers {
    static ClassifiedCustomers instance;
    private Customer[][] classifiedCustomers;

    private ClassifiedCustomers() {
    }

    public static ClassifiedCustomers getInstance() {
        if (instance == null) {
            instance = new ClassifiedCustomers();
        }
        return instance;
    }

    public Customer[][] getClassifiedCustomers() {
        return classifiedCustomers;
    }

    public void setClassifiedCustomers(Customer[][] classifiedCustomers) {
        this.classifiedCustomers = classifiedCustomers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassifiedCustomers)) return false;
        ClassifiedCustomers that = (ClassifiedCustomers) o;
        return Arrays.deepEquals(classifiedCustomers, that.classifiedCustomers);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(classifiedCustomers);
    }

    @Override
    public String toString() {
        return "ClassifiedCustomers{" +
                "classifiedCustomers=" + Arrays.toString(classifiedCustomers) +
                '}';
    }

    public void printByGroup() {
        for (int i = 0; i < classifiedCustomers.length; i++) {
            if(i==0){
                System.out.println("분류가 안된 고객 목록");
            }
            else if(i==1){
                System.out.println("GENERAL 고객 목록");
            }
            else if(i==2){
                System.out.println("VIP 고객 목록");
            }
            else if(i==3){
                System.out.println("VVIP 고객 목록");
            }
            for (int j = 0; j < classifiedCustomers[i].length; j++) {
                System.out.println(classifiedCustomers[i][j].toString());
            }
        }
    }

    public void printBySortedDetailNONE(Customer[] customers){
        System.out.println("분류가 안된 고객 명단");
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null){
                System.out.println(customers[i].toString());
            }        }
    }

    public void printBySortedDetailGENERAL(Customer[] customers){
        System.out.println("일반 등급 고객 명단");
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null){
                System.out.println(customers[i].toString());
            }        }
    }

    public void printBySortedDetailVIP(Customer[] customers){
        System.out.println("VIP 등급 고객 명단");
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null){
                System.out.println(customers[i].toString());
            }        }
    }

    public void printBySortedDetailVVIP(Customer[] customers){
        System.out.println("VVIP 등급 고객 명단");
        for (int i = 0; i < customers.length; i++) {
            if(customers[i]!=null){
                System.out.println(customers[i].toString());
            }
        }
    }

    public Customer[] getNONEGroup(){
        return classifiedCustomers[0];
    }

    public Customer[] getGENERALGroup(){
        return classifiedCustomers[1];
    }

    public Customer[] getVIPGroup(){
        return classifiedCustomers[2];
    }

public Customer[] getVVIPGroup(){
        return classifiedCustomers[3];
    }


    public Customer[] sortByNameAsc(Customer[] customers) {
        Customer[] customers1 = Arrays.copyOf(customers, customers.length);
        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o1.getName().compareTo(o2.getName());
                }
                return 0;
            }
        });
        return customers1;
    }

    public Customer[] sortByNameDesc(Customer[] customers) {
        Customer[] customers1 = Arrays.copyOf(customers, customers.length);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getName().compareTo(o1.getName());
                }
                return 0;
            }
        });
        return customers1;
    }



    public Customer[] sortBySpendMoneyDesc(Customer[] customers) {
        Customer[] customers1 = Arrays.copyOf(customers, customers.length);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getSpendMoney() - o1.getSpendMoney();
                } else {
                    return 0;
                }
            }
        });
        return customers1;
    }

    public Customer[] sortBySpendMoneyAsc(Customer[] customers) {
        Customer[] customers1 = Arrays.copyOf(customers, customers.length);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o1.getSpendMoney()- o2.getSpendMoney() ;
                } else {
                    return 0;
                }
            }
        });
        return customers1;
    }
    public Customer[] sortBySpendHourDesc(Customer[] customers) {
        Customer[] customers1 = Arrays.copyOf(customers, customers.length);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o2.getSpendHour() - o1.getSpendHour();
                } else {
                    return 0;

                }
            }
        });
        return customers1;
    }

    public Customer[] sortBySpendHourAsc(Customer[] customers) {
        Customer[] customers1 = Arrays.copyOf(customers, customers.length);

        Arrays.sort(customers1, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1 != null && o2 != null) {
                    return o1.getSpendHour()- o2.getSpendHour();
                } else {
                    return 0;

                }
            }
        });
        return customers1;
    }

}

