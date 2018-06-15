# gerenciador-senhas
Aplicativo para gerenciamento seguro de senhas.

# Requisitosfuncionaisobrigatórios:
 O aplicativo deve permitir o cadastro de usuários, as informações solicitadas são: nome, e-mail
e senha
◦ A senha deverá conter no mínimo 8 caracteres, dos quais deve possuir no mínimo 1 letra, 1
número e 1 caractere especial
◦ O cadastro do usuário deverá ser realizado contra a seguinte API //adicionar dados da api
 O aplicativo deverá permitir o login do usuário
◦ Após o login e o cadastro será disponibilizado um token para acesso as outras apis
◦ O login do usuário deverá ser efetuado contra a seguinte API //adicionar dados da api
 Após o cadastro ou o primeiro login do usuário naquele aparelho deverá ser solicitado o
cadastro da biometria do usuário (FingerPrint/TouchID)
◦ Os próximos acessos do usuário da aplicação poderão ser feitos por biometria ou digitando a
senha
 Após logado o aplicativo permitirá que o usuário adicione seus logins em sites para serem
armazenados de forma segura apenas no dispositivo, as informações solicitadas serão: url do
site, usuário/e-mail e senha
◦ O armazenamento dessas credenciais ficará apenas no dispositivo, se utilizando do
mecanismo seguro de cada plataforma como AKS (AndroidKeystore System) ou Keychain +
TouchID
 Deverá ser apresentado para o usuário como tela inicial da aplicação uma lista contendo todos
os sites cadastrados, onde deverá ser apresentado o ícone do mesmo, url e usuário/e-mail.
◦ O ícone poderá ser acessado pela seguinte API mediante requisição com o token//adicionar
dados da API
 Ao clicar em um site da lista deverá ser apresentado os detalhes do mesmo
◦ A exibição da senha poderá ser ocultada/mostrada
◦ A senha poderá ser copiada para a área de transferência
 Em um site cadastrado o usuário poderá editar ou excluir o mesmo.
Requisitosnãofuncionaisobrigatórios:
 O aplicativo deve ser desenvolvido em linguagem nativa da plataforma, sendo Java ou Kotlin
para Android.
 O código fonte deverá seguir as premissas de qualidade da plataforma. Será utilizada uma
ferramenta de verificação da qualidade do código fonte para efetuar a validação.
 O aplicativo deve possuir um visual agradável, boas práticas de UI/UX da plataforma devem ser
consideradas e seguidas.
 Devem ser implementados testes unitários se utilizando de ferramentas proporcionadas pela
plataforma
# Requisitos não funcionais não obrigatórios (extras):
 Implementações adicionais relacionadas com o contexto da aplicação
 UI/UX aprimoradas
 Realização de testes de interface
 Utilização de programação reativa (ReactiveX)
