public class Movie {

  public static final int  CHILDRENS = 2;
  public static final int  REGULAR = 0;
  public static final int  NEW_RELEASE = 1;

  private String _title;
  private int _priceCode;

  public Movie(String title, int priceCode) {
      _title = title;
      _priceCode = priceCode;
  }

  public int getPriceCode() {
      return _priceCode;
  }

  public void setPriceCode(int arg) {
     _priceCode = arg;
  }

  public String getTitle (){
      return _title;
  };
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

    double getCharge() { // veja que não precisa mais de parâmetro
     double result = 0;
     switch (getMovie().getPriceCode()) {
        case Movie.REGULAR:
           result += 2;
           if (getDaysRented() > 2)
              result += (getDaysRented() - 2) * 1.5;
           break;
        case Movie.NEW_RELEASE:
           result += getDaysRented() * 3;
           break;
        case Movie.CHILDRENS:
           result += 1.5;
           if (getDaysRented() > 3)
              result += (getDaysRented() - 3) * 1.5;
           break;
     }

     int getFrequentRenterPoints() {
       if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1)
          return 2;
       else
          return 1;
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