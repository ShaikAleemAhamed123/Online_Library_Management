
import java.lang.reflect.Array;
import java.util.*;

interface library{
    void issue_book(String book_name);
    void return_book(String book_name);
    void donate_book(String donor_name,String book_name);
}

abstract class libraryDetails{
    //Utility Variables........

    //static String[] initial_books={"maths","physics","chemistry","data structures","java programming","c programming","discrete mathematics","algorithms"};
    static ArrayList<String> books = new ArrayList<String>(Arrays.asList("maths", "physics", "chemistry", "data structures", "java programming", "c programming", "discrete mathematics", "algorithms"));
    static ArrayList<String> available = new ArrayList<String>(books);
    static ArrayList<String> issued = new ArrayList<String>();
    static ArrayList<String> issuedStudents=new ArrayList<>();
    static ArrayList<Integer> issuedBookCount=new ArrayList<Integer>();
    static ArrayList<ArrayList<String>> issuedBooks=new ArrayList<>();
    static ArrayList<String> donors=new ArrayList<>();
    static  ArrayList<Integer> donationCount=new ArrayList<>();
    static ArrayList<ArrayList<String>> donatedBooks=new ArrayList<>();
    //static List<Pair<String,Integer>> issue_details=new ArrayList<Pair<String, Integer>>();
    static int issued_count = 0;
    static int available_count = 8;
    static int total_donations=0;
}

 

//Online Library Class

public class onlinelibrary extends libraryDetails implements library{
    Scanner sc=new Scanner(System.in);
    public String studentName;
 

    //utility functions
    public static void issuedBooks(){
        System.out.print("Issued Books: ");
        System.out.println(issued);
    }
    public static void availableBooks(){
        System.out.print("Available Books: ");
        System.out.println(available);
    }
    public static void libraryDetails(){
        System.out.println();
        System.out.println("Total Books: "+(issued_count+available_count));
        System.out.println("Issued books: "+issued_count);
        System.out.println("Available books: "+available_count);
        System.out.println("Donated Books: "+total_donations);
        System.out.println();
        System.out.println("----AVAILABLE BOOKS----");
        System.out.println("Available books: "+available_count);
        System.out.println(available);
        System.out.println();
        System.out.println("----ISSUED BOOKS DETAILS----");
        System.out.println("Issued books: "+issued_count);
        for (int i=0;i<issuedStudents.size();i++) {
            System.out.print(issuedStudents.get(i)+" ");
            System.out.print(issuedBookCount.get(i)+" ");
            System.out.print(issuedBooks.get(i)+"\n");
        }
        System.out.println();
        System.out.println("----DONATIONS DETAILS----");
        System.out.println("Total Donated Books: "+total_donations);
        for (int i=0;i<donors.size();i++) {
            System.out.print(donors.get(i)+" ");
            System.out.print(donationCount.get(i)+" ");
            System.out.print(donatedBooks.get(i)+"\n");
        }
    }


    // Book Issuing.........

    public void issue_book(String book_name) {

        if (available.indexOf(book_name) != -1) {

            if(issuedStudents.indexOf(studentName)!=-1){
                int index=issuedStudents.indexOf(studentName);
                Integer value = issuedBookCount.get(index); // get value
                value = value + 1; // increment value
                issuedBookCount.set(index, value); // replace value
                (issuedBooks.get(index)).add(book_name);
            }
            else{
                issuedStudents.add(studentName);
                issuedBookCount.add(1);
                issuedBooks.add(new ArrayList<>(List.of(book_name)));

            }

            int index = available.indexOf(book_name);
            available_count--;
            issued_count++;
            issued.add(book_name);
            available.remove(index);
            System.out.println(book_name + " book issued Successfully.");

        } else {
            System.out.println("Sorry, Book not Available.!");
        }

    }
    //Returning the books.....

    public void return_book(String book_name) {


        if (issued.indexOf(book_name) != -1) {
            if(issuedStudents.indexOf(studentName)!=-1) {
                int index = issuedStudents.indexOf(studentName);
                Integer value = issuedBookCount.get(index); // get value
                value = value - 1; // increment value
                if (value == 0) {
                    issuedStudents.remove(index);
                    issuedBookCount.remove(index);
                    issuedBooks.remove(index);
                } else {
                    issuedBooks.get(index).remove(book_name);
                    issuedBookCount.set(index, value); // replace value
                }
            }
            int index = issued.indexOf(book_name);
            available_count++;
            issued_count--;
            available.add(book_name);
            issued.remove(index);
            System.out.println(book_name + " book returned Successfully.");

        }
        else {
            System.out.println("Please check and enter the correct name of the book..!");
        }
    }

    //Book Donation

    public void donate_book(String donor_name,String book_name){
        available_count++;
        available.add(book_name);
        if(donors.indexOf(donor_name)!=-1){
            int index=donors.indexOf(donor_name);
            int value=donationCount.get(index);
            value=value+1;
            donationCount.set(index,value);
            donatedBooks.get(index).add(book_name);
        }
        else{
            donors.add(donor_name);
            donationCount.add(1);
            donatedBooks.add(new ArrayList<>(List.of(book_name)));
        }
        total_donations++;
        System.out.println("Thank You for Donating "+book_name+" book for our library.");
    }


    public void issuedStudentDetails(String student_name){
        if(issuedStudents.indexOf(student_name)!=-1){
            int index=issuedStudents.indexOf(student_name);
            //System.out.println("Index:"+index); //used for debugging
            System.out.println("Student Name:"+student_name);
            System.out.println("Issued books count:"+issuedBookCount.get(index));
            System.out.println("Issued Books"+issuedBooks.get(index));
        }
        else{
            System.out.println("The student has no books issued to him..");
        }
    }

    public void book_borrow(){
        String student_name;
        String book_name;
        System.out.print("Enter your name:");
        student_name=sc.nextLine();
        studentName=student_name;
        System.out.print("Enter Book name:");
        book_name=sc.nextLine();    
        issue_book(book_name);
    }
    public void book_return(){
        String student_name;
        String book_name;
        System.out.print("Enter your name:");
        student_name=sc.nextLine();
        studentName=student_name;
        System.out.print("Enter Book name:");
        book_name=sc.nextLine();
        return_book(book_name);
    }
    public void book_donate(){
        String donor_name;
        System.out.print("Enter your name: ");
        donor_name=sc.nextLine();
        String book_name;
        System.out.print("Enter Book name: ");
        book_name=sc.nextLine();
        donate_book(donor_name,book_name);
    }
    public void available_books(){
        onlinelibrary.availableBooks();
    }
    public void your_details(){
        String student_name;
        System.out.print("Enter your name:");
        student_name=sc.nextLine();
        issuedStudentDetails(student_name);
    }


    static public void print_array(ArrayList A){
        for(int i=0;i<A.size();i++){
            System.out.print(A.get(i) + "  ");
        }
        System.out.println("");
    }

    public static void Exit(){
        System.out.print("\n\n");
        System.out.print("\n\t\t\t\t##########################################################");
        System.out.print("\n\t\t\t\t# Thanks for giving your valuable time in this project.  #");
        System.out.print("\n\t\t\t\t# Developed By : Aleem Ahamed Shaik                      #");
        System.out.print("\n\t\t\t\t# Roll Number  : 20115088                                #");
        System.out.print("\n\t\t\t\t# Branch       : Computer Science and Engineering        #");
        System.out.print("\n\t\t\t\t# Subject      : OOP USING JAVA                          #");
        System.out.print("\n\t\t\t\t##########################################################");
        System.out.print("\n");
        System.exit(0);
    }



    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        onlinelibrary OL1=new onlinelibrary();
        char ch;
        do{
            System.out.print("\n\t\t\t\t\t\tMENU");

            System.out.print("\n\t\t####################################################################");
            System.out.print("\n\t\t--------------------------------------------------------------------");
            System.out.print("\n\t\t|                                                                  |");
            System.out.print("\n\t\t|                    ONLINE LIBRARY                                |");
            System.out.print("\n\t\t|__________________________________________________________________|");
            System.out.print("\n\t\t|                                                                  |");
            System.out.print("\n\t\t|                     1. Borrow a Book                             |");
            System.out.print("\n\t\t|                     2. Return a Book                             |");
            System.out.print("\n\t\t|                     3. Donate a Book                             |");
            System.out.print("\n\t\t|                     4. Available Books                           |");
            System.out.print("\n\t\t|                     5. Your Details                              |");
            System.out.print("\n\t\t|                     6. Issued Books(only for Administrators)     |");
            System.out.print("\n\t\t|                     7. Library Details(only for Administrators)  |");
           // System.out.print("\n\t\t|                     5. Done                                      |");
            System.out.print("\n\t\t|                     8. Exit(Exits the entire Program)            |");
            System.out.print("\n\t\t--------------------------------------------------------------------");
            System.out.print("\n\t\tEnter your choice(1-8):");
            int choice=sc.nextInt();
            switch(choice)
            {
                case 1: OL1.book_borrow();
                    break;
                case 2: OL1.book_return();
                    break;
                case 3: OL1.book_donate();
                    break;
                case 4: OL1.available_books();
                    break;
                case 5: OL1.your_details();
                    break;
                case 6: onlinelibrary.issuedBooks();
                    break;
                case 7: onlinelibrary.libraryDetails();
                    break;
                case 8: onlinelibrary.Exit();
                default:System.out.print("Invalid choice!!!!");
            }
            System.out.print("\n\t\tDo you want to continue(y/n):");
            ch = sc.next().charAt(0);
            //System.out.print("\n\n"+ch);
            ch=Character.toLowerCase(ch);
        }while(ch!='n');
        onlinelibrary.Exit();

    }
}