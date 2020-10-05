import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Tugas2 {
    static double tinggi(double x, double a, double b){
        if (x<a) return 0;
        else if (x>b) return 1;
        else return (x-a)/(b-a);
    }
    static double rendah(double x, double a, double b){ 
        if (x<a) return 1;
        else if (x>b) return 0;
        else return (b-x)/(b-a);
    }
    static double rata(double x, double a, double b, double c, double d){
        if (x<a) return 0;
        else if (x>d) return 0;
        else if ((b<x) && (x<c)) return 1;
        else if ((x>a) && (x<b)) return tinggi(x,a,b);
        else return rendah(x,c,d);
    }
    static double min(double a, double b){
        return Math.min(a, b);
    }
    static double max(double a, double b, double c){
        return Math.max(a, Math.max(b, c));
    }
    static double defuzzi(String[] kel){
        return ((Double.parseDouble(kel[9])*100)+(Double.parseDouble(kel[10])*65)+(Double.parseDouble(kel[11])*45))/
                (Double.parseDouble(kel[9])+Double.parseDouble(kel[10])+Double.parseDouble(kel[11]));
    }
    static int[] sort(String[] data){
        int[] hasil={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double max;
        int idmax;
        for (int i = 0; i < 20; i++) {
            max=Double.parseDouble(data[i].split(",")[12]);
            idmax=i;
            for (int j = i; j < data.length; j++) {
                if (max<Double.parseDouble(data[j].split(",")[12])){
                    max=Double.parseDouble(data[j].split(",")[12]);
                    idmax=j;
                }
            }
            hasil[i]=Integer.parseInt(data[idmax].split(",")[0]);
            data[idmax]=data[i];
        }
        return hasil;
    } 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        double a = 0.4;    double i = 30;
        double b = 0.6;    double j = 40;
        double c = 0.5;    double k = 35;
        double d = 0.8;    double l = 45;
        double e = 1.3;    double m = 55;
        double f = 1.6;    double n = 65;
        double g = 1.4;    double o = 60;
        double h = 1.7;    double p = 70;
        String[] keluarga = new String[100];
        File file = new File("DataTugas2.csv");
        Scanner input = new Scanner(file);
        input.nextLine();
        int z=0;
        while(input.hasNext()){
             keluarga[z] = input.nextLine();
             keluarga[z]+=","+rendah(Double.parseDouble(keluarga[z].split(",")[1]),a,b);  //3 pendapatan rendah
             keluarga[z]+=","+rata(Double.parseDouble(keluarga[z].split(",")[1]),c,d,e,f); //4 pendaptan rata
             keluarga[z]+=","+tinggi(Double.parseDouble(keluarga[z].split(",")[1]),g,h); //5 pendapatan tinggi
             keluarga[z]+=","+rendah(Double.parseDouble(keluarga[z].split(",")[2]),i,j); //6 hutang rendah
             keluarga[z]+=","+rata(Double.parseDouble(keluarga[z].split(",")[2]),k,l,m,n); //7 hutang rata
             keluarga[z]+=","+tinggi(Double.parseDouble(keluarga[z].split(",")[2]),o,p); //8 hutang tinggi
             keluarga[z]+=","+max(min(Double.parseDouble(keluarga[z].split(",")[4]),Double.parseDouble(keluarga[z].split(",")[8])), 
                              min(Double.parseDouble(keluarga[z].split(",")[3]),Double.parseDouble(keluarga[z].split(",")[7])), 
                              min(Double.parseDouble(keluarga[z].split(",")[3]),Double.parseDouble(keluarga[z].split(",")[8])));  // Accepted 9
             keluarga[z]+=","+max(min(Double.parseDouble(keluarga[z].split(",")[5]),Double.parseDouble(keluarga[z].split(",")[8])), 
                              min(Double.parseDouble(keluarga[z].split(",")[4]),Double.parseDouble(keluarga[z].split(",")[7])), 
                              min(Double.parseDouble(keluarga[z].split(",")[3]),Double.parseDouble(keluarga[z].split(",")[6]))); //Considered 10
             keluarga[z]+=","+max(min(Double.parseDouble(keluarga[z].split(",")[5]),Double.parseDouble(keluarga[z].split(",")[7])), 
                              min(Double.parseDouble(keluarga[z].split(",")[5]),Double.parseDouble(keluarga[z].split(",")[6])), 
                              min(Double.parseDouble(keluarga[z].split(",")[4]),Double.parseDouble(keluarga[z].split(",")[6]))); // Rejected 11
             keluarga[z]+=","+defuzzi(keluarga[z].split(",")); //12
             z++;
        }
        int[] hasil=sort(keluarga);
        FileWriter fw = new FileWriter("TebakanTugas2.csv");
        try{
            for (int w = 0; w < hasil.length; w++) {
                fw.append(String.valueOf(hasil[w])+'\n');
            }
            fw.flush();
            fw.close();
        }catch(Exception v){}
    }
}