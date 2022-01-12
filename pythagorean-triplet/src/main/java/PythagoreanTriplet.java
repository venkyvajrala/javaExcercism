import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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

    int sideA;
    int sideB;
    int sideC;

    public PythagoreanTriplet(int sideA, int sideB, int sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public String toString() {
        return "PythagoreanTriplet{" +
                "sideA=" + sideA +
                ", sideB=" + sideB +
                ", sideC=" + sideC +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PythagoreanTriplet) {
            return ((PythagoreanTriplet) obj).sideA == sideA;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.sideA;
    }

    public static void sort(ArrayList<PythagoreanTriplet> triplets)
    {

        triplets.sort(Comparator.comparingInt(o -> o.sideA));
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
            int maxIteration = this.sum / 4;
            for(int number = 2; number< maxIteration; number+=2)
            {
                int n=(int)Math.pow(number,2)/2;

                double sqrtOfn = Math.sqrt(n) + 1;
                for(int divisor = 1; divisor< sqrtOfn; divisor++ )
                {
                    if(isDivisible(n, divisor))
                    {
                        
                        int  dividend= n/divisor;
                        int sideA=number+dividend;
                        int sideB=number+divisor;
                        int sideC=number+dividend+divisor;
                        int tripletSum=sideA+sideB+sideC;
                        int hypotenuse=Math.max(Math.max(sideA,sideB),sideC);
                        int base=Math.min(Math.min(sideA,sideB),sideC);
                        int height=tripletSum-hypotenuse-base;
                       if(tripletSum==this.sum)
                        {
                            triplets.add(new PythagoreanTriplet(base,height,hypotenuse));
                        }

                    }
                }
            }


            //Removing duplicate triplets from List
            HashSet<PythagoreanTriplet> hashSet = new HashSet(triplets);
            triplets= new ArrayList<>(hashSet);

            //sorting based on sideA
            sort((ArrayList<PythagoreanTriplet>) triplets);
            return triplets;
        }


        private boolean isDivisible(int n, int divisor) {
            return n % divisor == 0;
        }
    }




    



}