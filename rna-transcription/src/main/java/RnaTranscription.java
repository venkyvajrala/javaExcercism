class RnaTranscription {

    String transcribe(String dnaStrand) {

        String result="";
        int length=dnaStrand.length();
        char nucleotide='A' ;
        for(int index=0;index<length;index++)
        {

            switch (dnaStrand.charAt(index)) {
                case 'G' -> nucleotide = 'C';
                case 'C' -> nucleotide = 'G';
                case 'T' -> nucleotide = 'A';
                case 'A' -> nucleotide = 'U';

            }
            result+=nucleotide;

        }
        return result;
    }

}
