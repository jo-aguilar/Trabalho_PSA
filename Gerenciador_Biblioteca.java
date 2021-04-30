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
public class Gerenciador_Biblioteca{

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
            "[cad]: cadastro de indivíduos     [lcad]: mostra lista de indivíduos\n" +
            "[pub]: retirada e entrega de volumes\n" +
            "[lis]: mostrar esta mensagem novamente\n" +
            "[q]/[quit]: fechar o programa     [pend] Pendências do usuário\n" +
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
      System.out.print("Informe o tipo de brochura a ser inserida:\n"+
            " [livro]: livro \t [atlas]: atlas \t [revis]: revista\n>>");
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

      System.out.print("Informe o tipo de usuário a ser registrado:\n>" +
            "[usu]: usuário \t\t [func]: funcionário\n>>");
      tipo = teclado2.nextLine();
      while(true){
         if(tipo.equals("func") || tipo.equals("usu") ||
               tipo.equals("q") || tipo.equals("quit"))
            break;
         else{
            System.out.print("ERRO: tipo de usuário não definido. \n" +
                  "Entre com um tipo de usuário válido\n>>");
            tipo = teclado2.nextLine();
         }
      }
      if(tipo.equals("q") || tipo.equals("quit"))
         System.exit(0);
      else{
         System.out.print("Nome :      "); nome = teclado2.nextLine();
         System.out.print("RG   :      "); rg   = teclado2.nextInt();
         teclado2.nextLine(); //limpa o buffer
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
      //remove e adiciona indivíduos da lista de indivíduos, adicionando indis-
      //mininadamente de acordo com o status (usuário ou funcionário) e removendo
      //de acordo com o parâmetro especificado pelo administrador de fluxo
      Scanner teclado2 = new Scanner(System.in);
      String comando, entrada;

      System.out.print("Deseja remover ou adicionar cadastros à lista?\n" +
            "[rm]: remover \t\t [ad]:adicionar\n>>");
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

      if(comando.equals("rm")){
         //remoção
         if(lista.size()==0){
            //interrupção da rotina em caso de lista vazia
            System.out.println("Lista vazia: impossivel executar remoção.\n"+
                  "Retornando para menu principal...\n");
            return;
         }
         System.out.print("Procurar por indivíduo por nome[N/n] ou RG[R/r]?\n>> ");
         entrada = teclado2.nextLine();
         while(true){
            if(entrada.equals("R") || entrada.equals("N") ||
                  entrada.equals("r") || entrada.equals("n"))
               break;
            else{
               System.out.print("Por favor, entre com um tipo válido de busca.\n" +
                     "Nome[N/n] ou RG[R/r]\n>>: ");
               entrada = teclado2.nextLine();
            }
         }

         if(entrada.equals("R") || entrada.equals("r")){
            //procedimento para a inserção de um indivíduo na lista de usuários
            int rg;
            System.out.print("RG: ");
            rg = teclado2.nextInt();
            while(true){
               if(rg/100000000>0 && rg/100000000<10)
                  break;
               else{
                  System.out.println("Por favor, entre com um rg válido \nRG:");
                  rg = teclado2.nextInt();
                  teclado2.nextLine(); //limpa o buffer
               }
            }
            boolean check = remove_individuo(lista, rg);
            if(check==true)
               System.out.println("Removido...");
            else
               System.out.println("Cadastro inexistente...");
         }
         else{
            String nome;
            System.out.print("Nome: ");
            nome = teclado2.nextLine();
            boolean check = remove_individuo(lista, nome);
            if(check==true)
               System.out.println("Removido...");
            else
               System.out.println("Cadastro inexistente...");
         }
      }
      else{
         //procedimento para a inserção de um indivíduo na lista de usuários
         insere_individuo(lista);
      }
   }

   static public int retorna_indice_usuario(ArrayList<Individuo> lista, String usuario){
      //retorna o índice de um usuário buscado na lista caso ele esteja
      //cadastrado
      for(int clk = 0; clk<lista.size(); clk++){
         if((lista.get(clk).retornaNome()).equals(usuario)){
            return clk;
         }
      }
      return -1;
   }

   static public int retorna_indice_volume(ArrayList<Brochura> lista, String volume){
      //retorna o índice de uma brochura buscada na lista caso ela
      //exista nos registros da biblioteca
      for(int clk = 0; clk < lista.size(); clk++){
         if((lista.get(clk).retornaNome()).equals(volume))
            return clk;
      }
      return -1;
   }

   static public void admnistra_livros(ArrayList<Brochura>brochuras,
         ArrayList<Individuo> usuarios){
      //requisita e verifica a integridade dos dados de usuário e volume
      //a ser retirado das dependências da biblioteca. Em caso de integridadei
      //verifica a disponibilidade de retirada por quantidade de volumes e
      //falta de pendências por parte do usuário
      Scanner teclado2 = new Scanner(System.in);
      String procedimento;
      System.out.print("Efetuar retirada ou devolução de volume?\n" +
            "[ret]: retirada \t\t [dev]: devolução\n>>");
      procedimento = teclado2.nextLine();
      while(true){
         if(procedimento.equals("ret") || procedimento.equals("dev") ||
               procedimento.equals("quit") || procedimento.equals("q"))
            break;
         else{
            System.out.print("Procedimento não encontrado.\n"+
                  "Por favor, entre com um procedimento válido" +
                  "[ret]: retirada \t\t [dev]: devolução\n>>");
            procedimento = teclado2.nextLine();
         }
      }

      if(procedimento.equals("quit")||procedimento.equals("q"))
         System.exit(0);
      else{
         String nome, volume;

         System.out.print("Usuário: ");
         nome = teclado2.nextLine();
         System.out.print("Volume : ");
         volume = teclado2.nextLine();
         final int indice_usuario  = retorna_indice_usuario(usuarios, nome);
         final int indice_brochura = retorna_indice_volume(brochuras, volume);

         //usuário ou volume não encontrados entre dados da biblioteca
         if(indice_usuario==-1){
            System.out.println("Usuário não cadastrado. Impossível efetuar retirada\n"+
                  "Retornando ao menu principal...\n");
            return;
         }
         if(indice_brochura==-1){
            System.out.println("Volume não encontrado. Impossível efetuar retirada\n"+
                  "Retornando ao menu principal...\n");
            return;
         }


         //retirada
         if(procedimento.equals("ret")){
            //caso haja integridade dos dados, efetua-se a retirada, sendo o caso
            if(brochuras.get(indice_brochura).retornaInSitu()==0){
               System.out.println("Impossível executar retirada. Sem volumes disponíveis\n" +
                     "Retornando para o menu principal...\n");
               return;
            }
            if(brochuras.get(indice_brochura).retornaRetirada()==false){
               System.out.println("Impossível executar retirada. Atlas não podem ser retirados\n"+
                     "Retornado para o menu principal...\n");
            }
            brochuras.get(indice_brochura).diminuiInSitu();
            ((Usuario)usuarios.get(indice_usuario)).adicionaLivro((Livro)brochuras.get(indice_brochura));
         }
         else{

            //caso haja integridade dos dados, efetua-se a retirada, sendo o caso
            final int compA = brochuras.get(indice_brochura).retornaInSitu();
            final int compB = brochuras.get(indice_brochura).retornaDisponivel();
            if(compA==compB){
               System.out.println("Impossível executar devolução. Todos volumes já no local\n" +
                     "Retornando para o menu principal...");
               return;
            }
            //aumenta livros in situ da biblioteca e retira o mesmo título da lista de
            //volumes em posse do usuário
            ((Livro)brochuras.get(indice_brochura)).aumentaInSitu();
            ((Usuario)usuarios.get(indice_usuario)).retiraLivro(volume);
         }

      }
   }

   static public void mostra_pendencias(ArrayList<Individuo> lista){
      //mostra o registro do usuário e a lista de livros que ele tem
      //em posse no momento da consulta
      String nome;
      String entrada;
      int rg;
      Scanner teclado2 = new Scanner(System.in);
      System.out.print("Procurar por indivíduo por nome[N/n] ou RG[R/r]?\n>> ");
      entrada = teclado2.nextLine();
      while(true){
         if(entrada.equals("R") || entrada.equals("N") ||
               entrada.equals("r") || entrada.equals("n"))
            break;
         else{
            System.out.print("Por favor, entre com um tipo válido de busca.\n" +
                  "Nome[N/n] ou RG[R/r]\n>>: ");
            entrada = teclado2.nextLine();
         }
      }
      if(entrada.equals("R") || entrada.equals("r")){
         System.out.print("RG: ");
         rg = teclado2.nextInt();
         for(int clk = 0; clk < lista.size(); clk++){
            if((lista.get(clk)).retornaRGn()==rg){
               mostra_individuo(lista.get(clk));
               for(Brochura n: (((Usuario)lista.get(clk)).retornaLista()))
                  mostra_brochura(n);
               return;
            }
         }
      }
      else if(entrada.equals("N") || entrada.equals("n")){
         System.out.print("Nome: ");
         nome = teclado2.nextLine();
         for(int clk = 0; clk < lista.size(); clk++){
            if((lista.get(clk)).retornaNome().equals(nome)){
               mostra_individuo(lista.get(clk));
               for(Brochura n: (((Usuario)lista.get(clk)).retornaLista()))
                  mostra_brochura(n);
               return;
            }
         }
      }
         System.out.println("Sem lista de dados entrada.\n"+
                         "Retornando ao menu principal...\n");
      }


   /////////////////////////////////// MAIN ////////////////////////////////////////
   static public void main(String[]args){
      Scanner teclado = new Scanner(System.in);
      ArrayList<Brochura> livros = new ArrayList<Brochura>();
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
            administra_individuos(usuarios);
         }

         else if(entrada.equals("pub")){
            //efetua retirada ou devolução de volumes retirados pelo
            //usuário
            System.out.println("Retirada e entrega de volumes\n");
            admnistra_livros(livros, usuarios);
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
            //Caso o tamanho da lista de usuários seja igual a zero
            //mostra uma mensagem de erro
            if(usuarios.size()==0){
               System.out.println("Lista vazia: impossível executar visualização.\n"+
                     "Retornando para menu principal...\n");
            }
            else{
               label_usuario();
               for(Individuo n: usuarios)
                  mostra_individuo(n);
            }
         }
         else if(entrada.equals("pend")){
            //mostra o usuário requisitado pelo funcionário e quais livros
            //estão em sua posse no momento da consulta
            mostra_pendencias(usuarios);
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
      /////////////////////////// FIM DE MAIN ////////////////////////////////////////
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
      return (auxiliar.substring(0, 8) + "-" + auxiliar.substring(8,9));
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
   public void setaISBN(String isbn){ISBN = isbn;}
   public void diminuiInSitu(){
      //caso ainda haja livros in situ, diminuir em um volume (retirada)
      if(quantidade_in_situ>0)
         quantidade_in_situ--;
      else
         return;
      }
   public void aumentaInSitu(){
      //caso nem todos os livros estejam in situ, aumentar em um volume (entrega)
      if(quantidade_in_situ<quantidade_disponivel)
         quantidade_in_situ++;
      else
         return;
   }

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
   public void adicionaLivro(Livro livro){livros_retirados.add(livro);}
   public void removeLivro(Livro livro){livros_retirados.remove(livro);}
   public void setaExcedente(int dias){dias_excedentes = dias;}
   public float retornaMulta(){return setaMulta(dias_excedentes);}

   public void retiraLivro(String nome){
      //retira um livro da lista de livros em posse do usuário
      //caso exista
      for(int clk = 0; clk < livros_retirados.size(); clk++){
         if(livros_retirados.get(clk).retornaNome().equals(nome)){
            livros_retirados.remove(clk);
            return;
         }
      }
   }
}

class Funcionario extends Individuo{
   public Funcionario(String nn, int rrgg){
      //terceiro argumento de super em true implica em funcionários terem acesso
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
   public Livro(Livro livro){
      super(livro.retornaNome(),
            livro.retornaAutor(),
            1, true, true);
      super.setaISBN(livro.retornaISBN());
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
