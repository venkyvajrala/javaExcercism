
import java.util.ArrayList;
import java.util.List;

public class PythagoreanTriplet
{
    /* Conditions : x^2+y^2=z^2 , x<y<z  , x+y+z=sum
   Dickson's Method:
          to generate every triplet
          in the form  x^2+y^2=z^2
          find r,s,t such that r^2= 2*s*t  and r is an even integer
          then
              x=r+s
              y=r+t
              z=r+s+t
         r^2= 2*s*t
         =>s*t=r^2/2  ( s,t are factors of r^2/2)

         1.for every even integer r value find factors of r^2/2
         2.generate x,y,z from the factors
         3.check the sum x+y+z with given sum
         4.if equal add to triplets list

         Condition for termination: since sum=x+y+z = 2r+2s+2t  and s<r/2,t<r/2 => r<sum/4
                                    So, we loop for r values  2 to sum/4.
  */

    int tripletValue1;
    int tripletValue2;
    int tripletValue3;

    public PythagoreanTriplet(int tripletValue1, int tripletValue2, int tripletValue3) {
        this.tripletValue1 = tripletValue1;
        this.tripletValue2 = tripletValue2;
        this.tripletValue3 = tripletValue3;
    }

    public static TripletBuilder makeTripletsList() {
        return new TripletBuilder();
    }

    public  static  class TripletBuilder
    {
        int sum=-1;int maxValue=0;

        public TripletBuilder withFactorsLessThanOrEqualTo(int i) {

            maxValue=i;
            return this;
        }

        public TripletBuilder thatSumTo(int sum) {
            this.sum=sum;
            return this;
        }
        public List<PythagoreanTriplet> build() {

            List<PythagoreanTriplet> triplets = new ArrayList<>();
            for(int number = 2; number< (this.sum/4); number+=2)
            {
                int n=(int)Math.pow(number,2)/2;
                for(int divisor=1;divisor<Math.sqrt(n)+1;divisor++ )
                {
                    if(n%divisor==0)
                    {

                        int  dividend= n/divisor;
                        //System.out.println(divisor+":"+dividend+":"+number);
                        int tripletValue1=number+dividend;
                        int tripletValue2=number+divisor;
                        int tripletValue3=number+dividend+divisor;
                        int max=Math.max(Math.max(tripletValue1,tripletValue2),tripletValue3);
                        int min=Math.min(Math.min(tripletValue1,tripletValue2),tripletValue3);
                        int secondMax=tripletValue1+tripletValue2+tripletValue3-max-min;
                        //System.out.println(tripletValue1+","+tripletValue2+","+tripletValue3);
                       if(tripletValue1+tripletValue2+tripletValue3==this.sum)
                        {
                            System.out.println(min+","+secondMax+","+max);
                            triplets.add(new PythagoreanTriplet(min,secondMax,max));
                        }

                    }
                }
            }

            return triplets;
        }
    }








}