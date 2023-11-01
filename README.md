# Pedra, Papel e Tesoura

Este é um aplicativo de jogo simples de Pedra, Papel e Tesoura para Android, desenvolvido utilizando as mais recentes tecnologias do Android, incluindo Coroutines, Jetpack Compose, Material3, Room Database, DataStore, Retrofit2 e Hilt.

## Screenshots

<img src=https://github.com/ipirangad3v/RockPaperScissors/blob/master/screenshots/Screenshot_20231031_213916.png height=590/> <img src=https://github.com/ipirangad3v/RockPaperScissors/blob/master/screenshots/Screenshot_20231031_213933.png height=590/> <img src=https://github.com/ipirangad3v/RockPaperScissors/blob/master/screenshots/Screenshot_20231031_213947.png height=590/>

## Funcionalidades Principais

- **Jogar Pedra, Papel e Tesoura:** Jogue o clássico jogo contra o dispositivo com uma interface de usuário amigável usando o Jetpack Compose e o Material3 para um design moderno.

- **Salvar o histórico de jogos:** Os resultados de cada partida são armazenados localmente no banco de dados Room, permitindo que o usuário veja o histórico de jogos.

- **API que simula um matchmaking:** Os jogadores podem jogar com uma cpu remotamente usando os recursos do retrofit2 como cliente http.

- **Injeção de Dependência com Hilt:** O projeto faz uso do Hilt para gerenciar a injeção de dependência, garantindo um código limpo e fácil de manter.

## Pré-requisitos

- Android Studio com suporte às bibliotecas e plugins do Jetpack Compose, Material3, Room, DataStore, Retrofit2 e Hilt.

## Configuração do Projeto

1. Clone o repositório do projeto para a sua máquina local.

   ```shell
   git clone https://github.com/ipirangad3v/RockPaperScissors.git

2. Abra o projeto no Android Studio.

3. Execute o aplicativo em um emulador ou dispositivo Android.

## Tecnologias e Bibliotecas Utilizadas

- **Coroutines:** Utilizadas para executar tarefas assíncronas de forma concorrente.

- **Jetpack Compose:** Usado para construir a interface do usuário do aplicativo de forma declarativa.

- **Material3:** Adicionado para um design moderno e coerente com os princípios do Material Design.

- **Room Database:** Para armazenar o histórico de jogos localmente.

- **DataStore e Retrofit2:** Utilizados para lidar com a persistência de dados e fazer chamadas de API.

- **Hilt:** Para gerenciar a injeção de dependência no projeto.

## Estrutura do projeto
- **ui:** Contém a lógica e a interface do usuário do aplicativo.

- **data:** Contém as classes para lidar com a persistência de dados e chamadas de API.

- **di:** Configuração de injeção de dependência usando Hilt.
- **domain:** Contém a lógica de domínio do aplicativo.






