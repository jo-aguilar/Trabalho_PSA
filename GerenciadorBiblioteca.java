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

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

//============================================================================//
//                                     MAIN                                   //
//============================================================================//
public class GerenciadorBiblioteca{
   static public void main(String[]args){
      Scanner teclado = new Scanner(System.in);
      ArrayList<Brochura>  livros = new ArrayList<Brochura>();
      ArrayList<Individuo> usuarios = new ArrayList<Individuo>();
      String entrada;
      print_ajuda();

      while(true){
         System.out.print(">>");
         entrada = teclado.nextLine();

         if(entrada.equals("liv")){
         /*módulo de catálogo e grenciamento de brochuras, permitindo 
         inserção e retirada de elementos da lista de brochuras*/
            System.out.println("Gerenciamento de livros\n");
            insere_brochura(livros); //encapsular em outra função
         }

         else if(entrada.equals("cad")){
         /*módulo de registro e grenciamento de usuários, permitindo 
         inserção e retirada de elementos da lista de usuários*/
            System.out.println("Cadastro de pessoas\n");
            insere_individuo(usuarios); //encapsular em outra função
         }

         else if(entrada.equals("pub")){
            System.out.println("Retirada e entrega de volumes\n");
         }

         else if(entrada.equals("llivs")){
         //Mostra a lista de livros já catalogados e disponíveis 
         //nas dependências da bilbioteca
            label_livros();
            for(Brochura n: livros)
               mostra_brochura(n);
         }

         else if(entrada.equals("lcad")){
         //Mostra a lista de indivíduos já cadastrados e passíveis
         //ou não de retirar novos volumes das dependências da
         //biblioteca
            label_usuario();
            for(Individuo n: usuarios)
               mostra_individuo(n); 
         }
         else if(entrada.equals("lis"))
         //mostra novamente o cabeçalho do programa para reinformar
         //quais os comandos utilizáveis no menu principal
            print_ajuda();

         else if(entrada.equals("q") || entrada.equals("quit"))
         //termina o programa mediante solicitação do usuário
            System.exit(0);

         else
         //informa ao usuário a inexistência de um comando
           System.out.println("Comando não encontrado\n");
      }
   }

   ////////////////// FUNÇÕES INTERNAS DO OBJETO PRINCIPAL///////////////////////////

   static void print_ajuda(){
      System.out.println(
      //Lista de apresentação e comandos do menu principal do gerenciador
         "\n+==================================================================+\n"+
         "|                GERENCIADOR DE FLUXO - BIBLIOTECA                 |\n"+
         "+==================================================================+\n"+
         "Para o gerenciamento de fluxo, utilize o programa com os seguintes\n" + 
         "comandos:\n"+
         "[liv]: gerenciamento de livros    [lliv]: mostra lista de brochuras\n"+ 
         "[cad]: cadastro de indivíduos +   [lcad]: mostra lista de indivíduos\n" + 
         "[pub]: retirada e entrega de volumes\n" + 
         "[q]/[quit]: fechar o programa\n"+ 
         "[lis]: mostrar esta mensagem novamente\n" +
         "+==================================================================+\n"
      );
   }
   static void label_livros(){
   //idendificação de cabeçalho de função para se mostrar livros
   //cadastrados na biblioteca e seus respectivos dados
      for(int clk = 0; clk < 69; clk++){
         if((clk==0 || clk==21 || clk==42 || clk==57 || clk==62 || clk==68))
            System.out.print(".");
         else
            System.out.print("_");
         }
         System.out.println();
         System.out.printf("| %19s| %19s| %13s|%4s|%4s|", (String)"Livro", (String)"Autor",
                                                      (String)"ISBN",(String)"Qtd",
                                                      (String)"Disp");
         System.out.println();
         for(int clk = 0; clk < 69; clk++){
         if((clk==0 || clk==21 || clk==42 || clk==57 || clk==62 || clk==68))
            System.out.print(".");
         else
            System.out.print("_");
         }
         System.out.println();
   }

   static void label_usuario(){
   //idendificação de cabeçalho de função para se mostrar usuários
   //cadastrados na biblioteca e seus respectivos dados
      for(int clk = 0; clk < 58; clk++){
         if((clk==0 || clk==21 || clk==36 || clk==52 || clk ==57))
            System.out.print(".");
         else
            System.out.print("_");
         }
         System.out.println();
         System.out.printf("| %19s| %13s| %14s|%4s|", (String)"Nome", (String)"RG",
                                                      (String)"Status",(String)"Perm");
         System.out.println();
         for(int clk = 0; clk < 58; clk++){
            if((clk==0 || clk==21 || clk==36 || clk==52 || clk ==57))
               System.out.print(".");
            else
            System.out.print("_");
        }
        System.out.println();
   }

   static void mostra_brochura(Brochura bro){
   //teste para verificar a integridade do objeto Brochura
   /*Entrega os dados das brochuras de acordo com o que foi catalogado
   no módulo de catálogo para brochuras*/
      System.out.printf("%21s| %19s| %12s| %3d| %3d|\n", bro.retornaNome(), bro.retornaAutor(),
                                                   bro.retornaISBN(), bro.retornaDisponivel(),
                                                   bro.retornaInSitu());
   }

   static void mostra_individuo(Individuo ind){
   //teste para verificar a integridade do objeto Individuo
   /*Entrega os dados dos usuários de acordo com o que foi catalogado 
   no múdulo de catálogo para indivíduos (tanto funcionários quanto clientes)*/
      System.out.printf("%20s | %12s | %13s | %3s|\n", ind.retornaNome(), ind.retornaRG(),
                                                      ind.retornaStatus(),
                                                      (ind.retornaPermissao()==true?"S":"N"));
   }

   static void insere_brochura(ArrayList<Brochura> lista){
   /*Insere um dos 3 tipos básicos de brochura de acordo com especificado pelo
   funcionário, solicitando um dos 3 tipos de subclasses, mas não Brochura. Faz
   teste sobre a quantidade especificada de brochuras, devendo ser positiva e
   maior que 0*/
      Scanner teclado2 = new Scanner(System.in);
      String tipo, nome, autor;
      int quantidade;
      System.out.print("Informe o tipo de brochura a ser inserida:\n>>");
      tipo = teclado2.nextLine();
         while(true){
            if(tipo.equals("livro") || tipo.equals("revis") || 
               tipo.equals("atlas") || tipo.equals("q")|| tipo.equals("quit"))
               break;
            else{
                  System.out.println("ERRO: tipo de brochura não definido. \n" +
                               "Entre com um tipo de dado válido\n>>");
                  tipo = teclado2.nextLine();
               }
            }
         if(tipo.equals("q") || tipo.equals("quit"))
            System.exit(0);
         else{
            System.out.print("Nome :      "); nome = teclado2.nextLine();
            System.out.print("Autor:      "); autor = teclado2.nextLine();
            System.out.print("Quantidade: ");
            quantidade = teclado2.nextInt();
            while(true){
             if(quantidade>0)
               break;
             else{
                   System.out.println("Entrada foi de " + quantidade);
                   System.out.println("ERRO: quantidade inválida. \n" +
                                "Entre com uma quantidade válida de volumes\n>>");
                   quantidade = teclado2.nextInt();
                }
             }
           
            if(tipo.equals("livro"))
               lista.add(new Livro(nome, autor, quantidade));
            else if(tipo.equals("revis"))
               lista.add(new Revista(nome, autor, quantidade));
            else
               lista.add(new Atlas(nome, autor, quantidade));
            System.out.println("\n");
      }
   }

   static void insere_individuo(ArrayList<Individuo> lista){
   /*Insere um dos 3 tipos básicos de brochura de acordo com especificado pelo
   funcionário, solicitando um dos 3 tipos de subclasses, mas não Brochura. Faz
   teste sobre a quantidade especificada de brochuras, devendo ser positiva e
   maior que 0*/
      Scanner teclado2 = new Scanner(System.in);
      String nome, tipo;
      int rg;

      System.out.print("Informe o tipo de usuário a ser registrado:\n>>");
      tipo = teclado2.nextLine();
         while(true){
            if(tipo.equals("func") || tipo.equals("usu") || 
               tipo.equals("q") || tipo.equals("quit"))
               break;
            else{
                  System.out.println("ERRO: tipo de usuário não definido. \n" +
                               "Entre com um tipo de usuário válido\n>>");
                  tipo = teclado2.nextLine();
               }
            }
         if(tipo.equals("q") || tipo.equals("quit"))
            System.exit(0);
         else{
            System.out.print("Nome :      "); nome = teclado2.nextLine();
            System.out.print("RG   :      "); rg   = teclado2.nextInt();
            while(true){
             if(rg/100000000>0 && rg/100000000<10)
               break; //rg deve ter necessariamente 9 números válidos
             else{
                   System.out.println("Entrada foi de " + rg);
                   System.out.print("ERRO: numeração de RG inválida. \n" +
                                "Entre com uma numeração válida para RG\n>>");
                   rg = teclado2.nextInt();
                }
             }
           
            if(tipo.equals("func"))
               lista.add(new Funcionario(nome, rg));
            else
               lista.add(new Usuario(nome, rg));
      }
   }

   static boolean remove_individuo(ArrayList<Individuo> lista, String nn){
      //remove o indivíduo do cadastro de usuários a partir do nome, ou devolve
      //false caso o indivíduo não exista na lista como foi declarado seu nome
      for(int clk = 0; clk < lista.size(); clk++){
         if(lista.get(clk).retornaNome().equals(nn)){
            lista.remove(clk);
            return true;
         }
      }
      return false;
   }
   static boolean remove_individuo(ArrayList<Individuo> lista, int rrgg){
      //remove o indivíduo do cadastro de usuários a partir do seu rg, ou devolve
      //false caso o indivíduo não exista na lista como foi declarado seu rg
      for(int clk = 0; clk < lista.size(); clk++){
         if(lista.get(clk).retornaRGn()==rrgg){
            lista.remove(clk);
            return true;
         }
      }
      return false;
   }
   
   static void administra_individuos(ArrayList<Individuo> lista){
      Scanner teclado2 = new Scanner(System.in);
      String comando, entrada;
      
      System.out.println("Deseja remover ou adicionar cadastros à lista?\n" +
                         "[rm]: remover \t\t [ad]:adicionar\n");
      comando = teclado2.nextLine();
      while(true){
         if(comando.equalsIgnoreCase("rm") || comando.equalsIgnoreCase("ad"))
            break;
         else{
            System.out.print("Por favor, entre com um comando válido \n" + 
                             "[rm]: remover \t\t [ad]: adicionar\n>>");
            comando = teclado2.nextLine();
         }
      }

      if(comando.equalsIgnoreCase("rm")){
            //remoção
            System.out.print("Procurar por indivíduo por nome[N/n] ou RG[R/r]\n>>? ");
            entrada = teclado2.nextLine();
            while(true){
               if(entrada.equalsIgnoreCase("R") || entrada.equalsIgnoreCase("N"))
                     break;
               else{
                     System.out.print("Por favor, entre com um tipo válido de busca\n" +
                                       "Nome[N/n] ou RG[R/r]\n>>: ");
                     entrada = teclado2.nextLine();
               }
               
               if(entrada.equalsIgnoreCase("R")){
                  ;;//pedir, checar e entrar com número do RG
               }
               else{
                  ;;//pedir, checar e entrar com nome do indivíduo
               }
         }
      }
      else{
         //adicionar indivíduo | função já pronta
      }
   }
   

}

//============================================================================//
//                                 SUPERCLASSES                               //
//============================================================================//

class Individuo{
  /*Objeto básico para a criação de objetos relacionados aos indivíduos com algum
  vínculo com a biblioteca, podendo ser de usuários registrados a funcionários do
  local*/ 
  protected String nome;
  protected int rg;
  protected String status;   // status/cargo do indivíduo mediante a instituição
  protected boolean permissao_retirada;
  public Individuo(String nn, int rrgg, String ss, boolean pr){
    nome = nn;
    rg = rrgg;
    status = ss;
    permissao_retirada = pr;
  }
  String retornaNome(){return nome;}
  String retornaStatus(){return status;}
  boolean retornaPermissao(){return permissao_retirada;}
  String retornaRG(){
  //retorna o RG em forma de uma String legível sistematicamente
      String auxiliar = Integer.toString(rg);
      return (auxiliar.substring(0, 7) + "-" + auxiliar.substring(8));
  }
  int retornaRGn(){ return rg;}//retorna a versão numérica do RG
}



class Brochura{
   /*Objeto básico para a criação de objetos relacionados aos livros e análogos ca-
   talogados na biblioteca, podendo ser de revistas e livros e atlas para consulta */
   protected boolean retirada; //determina se a brochura pode ser retirada
   protected boolean devolucao;//determina se a brochura deve ser devolvida
   protected String nome;
   protected String autor;
   protected String ISBN;
   protected int quantidade_in_situ;
   protected int quantidade_disponivel;
   public Brochura(String nn, String aut, int qtd, boolean ret, boolean dev){
      nome = nn;
      autor = aut;
      quantidade_in_situ = qtd;
      quantidade_disponivel = qtd;
      ISBN = geraISBN().toString();
      retirada = ret;
      devolucao = dev;
   }
   public String retornaNome(){return nome;}
   public String retornaAutor(){return autor;}
   public String retornaISBN(){return ISBN;}
   public int retornaInSitu(){return quantidade_in_situ;}
   public int retornaDisponivel(){return quantidade_disponivel;}
   
   public boolean retornaRetirada(){return retirada;}
   public boolean retornaDevolucao(){return devolucao;}
   
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


////////////////////////////// SUBCLASSES DE INDIVIDUO /////////////////////////
class Usuario extends Individuo{
   private float valor_multa;
   private int dias_excedentes;
   private ArrayList<Brochura>livros_retirados = new ArrayList<Brochura>();
   //lista com as brochuras retiráveis alocadas pelo usuário
   //implica que revistas não são computadas (retirada permanente)
   //e atlas não são retiráveis
   public Usuario(String nn, int rrgg){
       super(nn, rrgg, "usuário", true);
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
   public ArrayList<Brochura> retornaLista(){return livros_retirados;}
   public void setaExcedente(int dias){dias_excedentes = dias;}
   public float retornaMulta(){return setaMulta(dias_excedentes);}

}

class Funcionario extends Individuo{
   public Funcionario(String nn, int rrgg){
   //terceiro argumento de super em true implia em funcionários terem acesso
   //irrestrito aos livros da biblioteca, sem necessidade de manutenção
   super(nn, rrgg, "funcionário", true);
   }
}

///////////////////////// SUBCLASSES DE BROCHURA ////////////////////////////////
class Livro extends Brochura{
//livro deve ser retirado e devolvido
   public Livro(String nn, String au, int qtd){
      super(nn, au, qtd, true, true);
   }
}

class Atlas extends Brochura{
//atlas não pode ser retirado
   public Atlas(String nn, String au, int qtd){
      super(nn, au, qtd, false, false);
   }
}

class Revista extends Brochura{
//revista pode ser retirada e não necessita de devolução
   public Revista(String nn, String au, int qtd){
      super(nn, au, qtd, true, false);
   }
}
