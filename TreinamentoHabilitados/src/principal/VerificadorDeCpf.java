package principal;
/**
 *
 * @author Vitor
 * 
 * Essa classe faz a verificação de todos os CPF's digitados em qualquer um dos forms da aplicação
 * Se o cpf for verdadeiro então retornaremos Verdadeiro do contrario o retorno será Falso.
 */
public class VerificadorDeCpf {
    private String cpf;
    
    public boolean verificarCpf (String cpf){
        boolean validado = true;
        this.cpf = cpf;
        String [] vet = this.cpf.split(".");
        this.cpf = vet[0]+vet[1];
        
       
        
        //Eta
        
        
        
        return validado;
    }
    
    
    
}
