package principal;
/**
 *
 * @author Vitor
 * 
 * Essa classe faz a verificaÃ§Ã£o de todos os CPF's digitados em qualquer um dos forms da aplicaÃ§Ã£o
 * Se o cpf for verdadeiro entÃ£o retornaremos Verdadeiro do contrario o retorno sera  Falso... 
 */
public class VerificadorDeCpf {
    private String cpf;
    
    public boolean verificarCpf (String cpf){
        boolean validado = true;
        String sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10, sub11;
        int dig1, dig2, dig3, dig4, dig5, dig6, dig7, dig8, dig9, dig10, dig11;
        int dv1, dv2, dvAux;
        int somaDig;
        this.cpf = cpf;
        
        String [] vet = this.cpf.split("\\.");   // Instruções para 
        this.cpf =  vet[0]+vet[1]+vet[2];       // Tirar os pontos e o traço da String do cpf
        vet = this.cpf.split("\\-");            // que foi inserido na txtCpf dos forms
        this.cpf = vet[0]+vet[1];
       
       sub1 = this.cpf.substring(0, 1);  // Instruções para quebrar  
       sub2 = this.cpf.substring(1, 2);  // Cada caracter da String 
       sub3 = this.cpf.substring(2, 3);  // e dividi-los individualmente.
       sub4 = this.cpf.substring(3, 4);
       sub5 = this.cpf.substring(4, 5);
       sub6 = this.cpf.substring(5, 6);
       sub7 = this.cpf.substring(6, 7);
       sub8 = this.cpf.substring(7, 8);
       sub9 = this.cpf.substring(8, 9);
       sub10 = this.cpf.substring(9, 10);
       sub11 = this.cpf.substring(10, 11);
       
       
       dig1 = Integer.parseInt(sub1);   // Instruções para transformar
       dig2 = Integer.parseInt(sub2);   // String em Int
       dig3 = Integer.parseInt(sub3);
       dig4 = Integer.parseInt(sub4);
       dig5 = Integer.parseInt(sub5);
       dig6 = Integer.parseInt(sub6);
       dig7 = Integer.parseInt(sub7);
       dig8 = Integer.parseInt(sub8);
       dig9 = Integer.parseInt(sub9);
       
       //Digito Verificador informado pelo usuario
       // Esta sendo transformado em Int 
       
       dig10 = Integer.parseInt(sub10);
       dig11 = Integer.parseInt(sub11);
       
       
       
       // Abaixo começa a logica de verificação da veracidade do cpf
       // informado pelo usuario em qualquer campo da aplicação que exiga tal dado
       
       dig1 = dig1*10;
       dig2 = dig2*9;
       dig3 = dig3*8;
       dig4 = dig4*7;
       dig5 = dig5*6;
       dig6 = dig6*5;
       dig7 = dig7*4;
       dig8 = dig8*3;
       dig9 = dig9*2;
       
       somaDig = dig1+dig2+dig3+dig4+dig5+dig6+dig7+dig8+dig9;
       
       dv1 = somaDig%11;
       
       if(dv1 < 2){
           dv1 = 0;
       }else {
           dv1 = (-dv1) + 11;
       }
       dvAux = dv1; // dvAux armazena o primeiro digito verificador
                    // Pois se não o seu valor será perdido porque ele participa
                    // da verificação do segundo digito verificador.
       
       
       
       // Começaremos a verificação do segundo digito verificador, retornaremos
       // assim os valores iniciais dos digitoa antes da multiplicação
       
       dig1 = Integer.parseInt(sub1);   
       dig2 = Integer.parseInt(sub2);   
       dig3 = Integer.parseInt(sub3);
       dig4 = Integer.parseInt(sub4);
       dig5 = Integer.parseInt(sub5);
       dig6 = Integer.parseInt(sub6);
       dig7 = Integer.parseInt(sub7);
       dig8 = Integer.parseInt(sub8);
       dig9 = Integer.parseInt(sub9);
       
       
       dig1 = dig1*11;
       dig2 = dig2*10;
       dig3 = dig3*9;
       dig4 = dig4*8;
       dig5 = dig5*7;
       dig6 = dig6*6;
       dig7 = dig7*5;
       dig8 = dig8*4;
       dig9 = dig9*3;
       dv1 = dv1*2;
       
       somaDig = dig1+dig2+dig3+dig4+dig5+dig6+dig7+dig8+dig9+dv1;
       
        dv2 = somaDig%11;
        
        if(dv2 < 2){
            dv2 = 0;
        }else {
            dv2 = (-dv2) + 11;
        }
        
        
        // Faz a verificação do cpf digitado pelo usuario.
        
        if(this.cpf.equals("00000000000")){
            return validado = false;
        }else if(this.cpf.equals("11111111111")){
            return validado = false;
        }else if (this.cpf.equals("22222222222")){
            return validado = false;
        }else if (this.cpf.equals("33333333333")){
            return validado = false;
        }else if (this.cpf.equals("44444444444")){
            return validado = false;
        }else if (this.cpf.equals("55555555555")){
            return validado = false;
        }else if (this.cpf.equals("66666666666")){
            return validado = false;
        }else if (this.cpf.equals("77777777777")){
            return validado = false;
        }else if (this.cpf.equals("88888888888")){
            return validado = false;
        }else if (this.cpf.equals("99999999999")){
            return validado = false;
        }else if (dig10 != dvAux || dig11 != dv2){
            return validado = false;
        }else {
            return validado = true;
            
        }
        
        
    }
    
    
    
}
