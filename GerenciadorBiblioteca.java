/*
Gerenciador para inserção de clientes de uma biblioteca.
Tipos de objetos:
Indivíduo (classe geral para pessoas)
Usuários:   acesso negado ao sistema mediante senha.
            Permissão para retirada de livros caso haja devolução e multas pagas.
Funcionário: acesso permitido ao sistema mediante senha.
             Permissão irrestrita de retirada de livros, inclusive revistas e
             atlas. Sem restrição para entrega de livros

Brochura (classe geral para encadernados)
Livro: retirada da biblioteca permitida
Revista: retirada da biblioteca proibida
Atlas: retirada da biblioteca proibida
*/

//============================================================================//
//                                 SUPERCLASSES                               //
//============================================================================//
import java.util.Random;
import java.util.ArrayList;

class Individuo{
  /*Objeto básico para a criação de objetos relacionados aos indivíduos com algum
  vínculo com a biblioteca, podendo ser de usuários registrados a funcionários do
  local*/ 
  protected String nome;
  protected String status;   // status/cargo do indivíduo mediante a instituição
  protected boolean permissao_retirada;
  public Individuo(String nn, String ss, boolean pr){
    nome = nn;
    status = ss;
    permissao_retirada = pr;
  }
  String retornaNome(){return nome;}
  String retornaStatus(){return status;}
  boolean retornaPermissao(){return permissao_retirada;}
}



class Brochura{
   /*Objeto básico para a criação de objetos relacionados aos livros e análogos ca-
   talogados na biblioteca, podendo ser de revistas e livros e atlas para consulta */
   protected String nome;
   protected String autor;
   protected String ISBN;
   protected int quantidade_in_situ;
   protected int quantidade_disponivel;
   public Brochura(String nn, String aut, int qtd){
      nome = nn;
      autor = aut;
      quantidade_in_situ = qtd;
      quantidade_disponivel = qtd;
      ISBN = geraISBN().toString();
   }
   public String retornaNome(){return nome;}
   public String retornaAutor(){return autor;}
   public String retornaISBN(){return ISBN;}
   public int retornaInSitu(){return quantidade_in_situ;}
   public int retornaDisponivel(){return quantidade_disponivel;}
   private StringBuilder geraISBN(){
   /*Gera um registro aleatório de ISBN para que uma brochura qualquer possa
   ser catalogada no sistema  de livros*/
      StringBuilder resultado = new StringBuilder();
      Random rand = new Random();
      for(int clk = 0; clk < 13; clk++){
         if(clk==4 || clk==6 || clk==11)
            resultado.append("-");
         else
            resultado.append(Integer.toString(rand.nextInt(9)));
      }
      return resultado;
   }
}

//============================================================================//
//                                   SUBCLASSES                               //
//============================================================================//

class Usuario extends Individuo{
   private float valor_multa;
   private int dias_excedentes;
   public Usuario(String nn){
       super(nn, "usuário", true);
       valor_multa = 5;
   }
   public void setaPermissao(boolean pp){
     //modifica a permissão do Usuário para retirada ou não de livros, pois,
     //diferente de funcionários, eles não têm acesso irrestrito aos livros
     permissao_retirada = pp;
   }
   public float setaMulta(int tempo){
      //multa aplicada em 10% cumulativos do valor original sobre a multa
      //original;
      float multa_aux = valor_multa;
      for(int clk = 0; clk < tempo; clk++){
         multa_aux *= 1.1;
      }
      return multa_aux;
   }
   public void setaExcedente(int dias){dias_excedentes = dias;}
   public float retornaMulta(){return setaMulta(dias_excedentes);}

}

class Funcionario extends Individuo{
   public Funcionario(String nn){
   //terceiro argumento de super em true implia em funcionários terem acesso
   //irrestrito aos livros da biblioteca, sem necessidade de manutenção
   super(nn, "funcionário", true);
   }
}



public class GerenciadorBiblioteca{
   //fazer função para a devolução e reincremento de título
   
   /*
   Individuo     : funcionando
   Usuario       : funcionando
   Funcionario   : funcionando
   */

//============================================================================//
//                                     MAIN                                   //
//============================================================================//
   static public void main(String[]args){
      Brochura livro = new Brochura("A Tempestade", "Shakespeare", 20);
      mostra_brochura(livro);
      Individuo usuario = new Individuo("Joacir França", "Usuário", true);
      mostra_individuo(usuario);
      Funcionario func = new Funcionario("Antonio Neves");
      mostra_individuo(func);
      Usuario us = new Usuario("João Dias");
      mostra_individuo(us);
      System.out.println(us.setaMulta(5));
   }
//============================================================================//
//                                 FIM DE MAIN                                //
//============================================================================//



   //teste para verificar a integridade do objeto Brochura
   static public void mostra_brochura(Brochura bro){
      System.out.println("Nome do livro        : " + bro.retornaNome());
      System.out.println("Nome do autor        : " + bro.retornaAutor());
      System.out.println("ISBN                 : " + bro.retornaISBN());
      System.out.println("Quantidade disponível: " + bro.retornaDisponivel());
      System.out.println("Quantidade in situ   : " + bro.retornaInSitu() + "\n");
   }

   //teste para verificar a integridade do objeto Individuo
   static public void mostra_individuo(Individuo ind){
      System.out.println("Nome do individuo     : " + ind.retornaNome());
      System.out.println("Status do individuo   : " + ind.retornaStatus());
      System.out.println("Permissão do indivíduo: " + ind.retornaPermissao() + "\n");
   }

}
