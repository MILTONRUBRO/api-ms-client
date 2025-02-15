Feature: Buscar usuário pelo número do CPF

    Scenario: Buscar usuario existente pelo numero do CPF
        Given que o usuario com CPF "123.456.789-00" existe no sistema
        When eu buscar o usuario pelo CPF "123.456.789-00"
        Then o sistema deve retornar os dados do usuario com CPF "123.456.789-00"
        And o status da resposta deve ser 200