public class Movie {

  private Price _price;

   public Movie(String name, int priceCode) {
      _title = name;
      setPriceCode(priceCode);
   }
    
   public int getPriceCode() {
      return _price.getPriceCode();
   }
   
   public void setPriceCode(int arg) {
      switch (arg) {
         case REGULAR:
            _price = new RegularPrice();
            break;
         case CHILDRENS:
            _price = new ChildrensPrice();
            break;
         case NEW_RELEASE:
            _price = new NewReleasePrice();
            break;
         default:
            throw new IllegalArgumentException("Incorrect Price Code");
      }
   }
};

abstract class Price {
   abstract int getPriceCode();
}
 
class ChildrensPrice extends Price {
   int getPriceCode() {
       return Movie.CHILDRENS;
   }
}
 
class NewReleasePrice extends Price {
   int getPriceCode() {
       return Movie.NEW_RELEASE;
   }
}
 
class RegularPrice extends Price {
   int getPriceCode() {
       return Movie.REGULAR;
   }
}

class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
      _movie = movie;
      _daysRented = daysRented;
    }
    public int getDaysRented() {
      return _daysRented;
    }
    public Movie getMovie() {
      return _movie;
    }

     double getCharge() {
      return _movie.getCharge(_daysRented);
      }

      int getFrequentRenterPoints() {
       return _movie.getFrequentRenterPoints(_daysRented);
   }
}

class Customer {
   private String _name;
   private Vector _rentals = new Vector();

   public Customer (String name){
      _name = name;
   };

   public void addRental(Rental arg) {
      _rentals.addElement(arg);
   }
   public String getName (){
      return _name;
   };

   public String statement() {
   Enumeration rentals = _rentals.elements();
   String result = "Rental Record for " + getName() + "\n";
   while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();

      // show figures for this rental
      result += "\t" + each.getMovie().getTitle()+ "\t" +
                String.valueOf(each.getCharge()) + "\n";
   }

   // add footer lines
   result +=  "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
   result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) +
                   " frequent renter points";
   return result;
}

public String htmlStatement() {
   Enumeration rentals = _rentals.elements();
   String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
   while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();
      // show figures for each rental
      result += each.getMovie().getTitle()+ ": " +
                String.valueOf(each.getCharge()) + "<BR>\n";
   }
   
   // add footer lines
   result +=  "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
   result += "On this rental you earned <EM>" +
          String.valueOf(getTotalFrequentRenterPoints()) +
          "</EM> frequent renter points<P>";
   return result;
}
    
    private double getTotalCharge() {
       double result = 0;
       Enumeration rentals = _rentals.elements();
       while (rentals.hasMoreElements()) {
          Rental each = (Rental) rentals.nextElement();
          result += each.getCharge();
       }
       return result;
     }

     private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
           Rental each = (Rental) rentals.nextElement();
           result += each.getFrequentRenterPoints();
        }
        return result;
     }
}

private double amountFor(Rental aRental) {
   return aRental.getCharge();;
}

m1 = Movie("Titanic", 10)
m2 = Movie("MIB", 12)
m3 = Movie("Leaving Las Vegas", 20)
c = Customer ("Fernanda")
c.addRental(Rental(m1, 1)
c.addRental(Rental(m2, 12)
c.addRental(Rental(m3, 178)
res = c.statement()