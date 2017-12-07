-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 07-Dez-2017 às 03:32
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `leslivros`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cartao`
--

CREATE TABLE `cartao` (
  `id_cartao` int(11) NOT NULL,
  `numero` varchar(30) NOT NULL,
  `bandeira` varchar(100) NOT NULL,
  `dtVencimento` varchar(10) NOT NULL,
  `codigo_seg` varchar(20) NOT NULL,
  `pk_cliente` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cartao`
--

INSERT INTO `cartao` (`id_cartao`, `numero`, `bandeira`, `dtVencimento`, `codigo_seg`, `pk_cliente`) VALUES
(1, '1112223334455', 'MasterCard', '09/2022', '655', 5),
(4, '123124124', 'MasterCard', '09/2022', '667', 7),
(5, '124545', 'MasterCard', '09/2022', '123', 8),
(15, '123124124', 'MasterCard', '09/2022', '667', 9),
(14, '1112223334455', 'American Express', '01/26', '655', 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nome_categoria` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nome_categoria`) VALUES
(1, 'Informática'),
(2, 'Administração');

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria_livro`
--

CREATE TABLE `categoria_livro` (
  `id` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_subcategoria` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria_livro`
--

INSERT INTO `categoria_livro` (`id`, `id_livro`, `id_categoria`, `id_subcategoria`) VALUES
(1, 12, 2, 8),
(2, 13, 2, 8),
(3, 14, 2, 8),
(4, 16, 2, 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `genero` varchar(15) NOT NULL,
  `nome_cliente` varchar(100) NOT NULL,
  `dtNascimento` date NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `tipo` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `genero`, `nome_cliente`, `dtNascimento`, `cpf`, `email`, `senha`, `status`, `tipo`) VALUES
(5, 'M', 'Marco', '1965-07-23', '11122233344', 'teste', '123', 1, 0),
(6, 'M', 'Lucas Julio Alves', '1997-07-25', '11122233344', 'lucasjulio', '123', 1, 0),
(7, 'M', 'Felipe', '2000-08-23', '4443332221100', 'felipe@teste.com', '123', 1, 0),
(8, 'M', 'Fabio', '1996-08-01', '55544433322', 'fabio@teste.com', '123', 1, 0),
(9, 'M', 'Admin', '1997-07-23', '11122233344', 'Admin', '123', 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cupom`
--

CREATE TABLE `cupom` (
  `id_cupom` int(11) NOT NULL,
  `codigo` varchar(6) NOT NULL,
  `desconto` double(4,2) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `dtVencimento` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cupom`
--

INSERT INTO `cupom` (`id_cupom`, `codigo`, `desconto`, `tipo`, `dtVencimento`) VALUES
(1, 'aaa111', 20.00, 0, '2017-12-29'),
(2, 'bbb222', 30.00, 0, '2017-11-20'),
(3, 'ccc333', 40.00, 0, '2017-11-29');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cupom_pedido`
--

CREATE TABLE `cupom_pedido` (
  `id` int(11) NOT NULL,
  `fk_cupom` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cupom_pedido`
--

INSERT INTO `cupom_pedido` (`id`, `fk_cupom`, `fk_pedido`) VALUES
(1, 1, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id_endereco` int(11) NOT NULL,
  `tipo_res` varchar(30) NOT NULL,
  `tipo_log` varchar(200) NOT NULL,
  `logradouro` varchar(200) NOT NULL,
  `num_casa` varchar(6) NOT NULL,
  `bairro` varchar(60) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(100) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `pk_cliente` int(11) NOT NULL,
  `nome_id` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id_endereco`, `tipo_res`, `tipo_log`, `logradouro`, `num_casa`, `bairro`, `cep`, `cidade`, `estado`, `pais`, `pk_cliente`, `nome_id`) VALUES
(6, 'TEste', 'Teste', 'Teste', '123', 'Morte', '0114687', 'Suzano', 'SP', 'Brasil', 5, 'Teste'),
(20, 'Apartamento', 'Avenida', 'R. Teste', '123', 'Jardim Teste', '08676250', 'Suzano', 'SP', 'Brasil', 9, 'Casa 123'),
(8, 'Casa', 'Teste', 'Teste', '801', 'Teste', '12345520', 'Suzno', 'SP', 'Brasil', 7, 'Casa'),
(9, 'Apartamento', 'Avenida', 'R. Teste', '100', 'Jardim Teste', '08676250', 'Suzano', 'SP', 'Brasil', 6, 'Trabalho'),
(16, 'Apartamento', 'Avenida', 'R. Teste', '1234', 'Jardim Teste', '06676250', 'Mogi', 'SP', 'Brasil', 6, 'Casa'),
(19, 'Casa', 'Rua', 'Teste', '801', 'Bairro', '08676250', 'Ferraz', 'SP', 'Brasil', 9, 'Casa'),
(12, 'Casa', 'Rua', 'Teste', '801', 'Bairro', '08676250', 'Ferraz', 'SP', 'Brasil', 8, 'Casa');

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupoprecificacao`
--

CREATE TABLE `grupoprecificacao` (
  `id_grupo` int(11) NOT NULL,
  `nome_grupo` varchar(40) NOT NULL,
  `taxa` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `grupoprecificacao`
--

INSERT INTO `grupoprecificacao` (`id_grupo`, `nome_grupo`, `taxa`) VALUES
(1, 'A', 0),
(2, 'B', 15);

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido`
--

CREATE TABLE `item_pedido` (
  `id_item_pedido` int(11) NOT NULL,
  `fk_livro` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double(4,2) DEFAULT NULL,
  `PK_PEDIDO` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `item_pedido`
--

INSERT INTO `item_pedido` (`id_item_pedido`, `fk_livro`, `quantidade`, `preco`, `PK_PEDIDO`) VALUES
(9, 16, 2, 50.70, 6),
(10, 16, 2, 50.70, 7),
(11, 14, 2, 50.50, 7),
(12, 13, 2, 50.80, 7),
(13, 12, 2, 50.60, 7),
(14, 16, 1, 50.70, 8);

-- --------------------------------------------------------

--
-- Estrutura da tabela `livros`
--

CREATE TABLE `livros` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `autor` varchar(60) NOT NULL,
  `ano` varchar(4) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `editora` varchar(40) NOT NULL,
  `edicao` varchar(4) NOT NULL,
  `isbn` varchar(14) NOT NULL,
  `num_paginas` varchar(5) NOT NULL,
  `sinopse` text NOT NULL,
  `altura` double NOT NULL,
  `peso` double NOT NULL,
  `profundidade` double NOT NULL,
  `pk_grupo` int(11) NOT NULL,
  `qtde_estoque` int(11) DEFAULT NULL,
  `preco_livro` double(4,2) DEFAULT NULL,
  `largura` varchar(6) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `livros`
--

INSERT INTO `livros` (`id`, `nome`, `autor`, `ano`, `status`, `titulo`, `editora`, `edicao`, `isbn`, `num_paginas`, `sinopse`, `altura`, `peso`, `profundidade`, `pk_grupo`, `qtde_estoque`, `preco_livro`, `largura`) VALUES
(12, 'Nudge', 'Richard Thaler', '2009', 1, 'Nudge', 'Penguin Books UK', '1', '9780141040011', '100', 'Sinopse', 19, 13, 2, 2, 2, 50.60, '14.00'),
(13, 'Building Resilience', 'Sandberg, Sheryl / Grant, Adam', '2017', 1, 'Building Resilience', 'Knopf', '1', '9781524711214', '240', 'Livro', 24.1, 0.44, 2.5, 1, 2, 50.80, '15.00'),
(14, 'The Founder\'s Mentality', 'Allen, James / Zook, Chris', '2016', 1, 'The Founder\'s Mentality', 'Harvard Business Review Press', '1', '9781633691162', '224', 'Sinopse', 23.62, 0.41, 2.29, 2, 2, 50.50, '16.00'),
(16, 'Facing Adversity', 'Sandberg, Sheryl / Grant, Adam', '2017', 1, 'Facing Adversity', 'Knopf', '1', '9781524711214', '240', 'Livro', 24.1, 0.44, 2.5, 1, 2, 50.70, '17.00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento_cartao`
--

CREATE TABLE `pagamento_cartao` (
  `id_pagamento_cartao` int(11) NOT NULL,
  `fk_cartao` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL,
  `valor` double(5,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pagamento_cartao`
--

INSERT INTO `pagamento_cartao` (`id_pagamento_cartao`, `fk_cartao`, `fk_pedido`, `valor`) VALUES
(10, 14, 8, 71.14),
(9, 14, 7, 448.65),
(8, 14, 6, 121.84);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `dtPedido` date NOT NULL,
  `status` varchar(20) NOT NULL,
  `pk_cliente` int(11) NOT NULL,
  `precoTotal` double(5,2) DEFAULT NULL,
  `frete` double(4,2) DEFAULT NULL,
  `fk_endereco` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`id`, `dtPedido`, `status`, `pk_cliente`, `precoTotal`, `frete`, `fk_endereco`) VALUES
(7, '2017-12-06', 'ENTREGUE\n', 6, 448.65, 43.45, 9),
(6, '2017-12-04', 'REPROVADO\n', 6, 121.84, 20.44, 9),
(8, '2017-12-06', 'ENTREGUE\n', 6, 71.14, 20.44, 9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_troca`
--

CREATE TABLE `pedido_troca` (
  `id` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `dtTroca` date NOT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido_troca`
--

INSERT INTO `pedido_troca` (`id`, `fk_pedido`, `fk_usuario`, `dtTroca`, `status`) VALUES
(4, 7, 6, '2017-12-07', 'EM TROCA\n');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_troca_itens`
--

CREATE TABLE `pedido_troca_itens` (
  `id_pedido_troca_itens` int(11) NOT NULL,
  `fk_pedido_troca` int(11) NOT NULL,
  `fk_livro` int(11) NOT NULL,
  `qtde_livro` int(11) NOT NULL,
  `preco_troca` double(4,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido_troca_itens`
--

INSERT INTO `pedido_troca_itens` (`id_pedido_troca_itens`, `fk_pedido_troca`, `fk_livro`, `qtde_livro`, `preco_troca`) VALUES
(3, 4, 16, 1, 50.70);

-- --------------------------------------------------------

--
-- Estrutura da tabela `subcategoria`
--

CREATE TABLE `subcategoria` (
  `id_subcategoria` int(11) NOT NULL,
  `nome_subcategoria` varchar(40) NOT NULL,
  `pk_categoria` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `subcategoria`
--

INSERT INTO `subcategoria` (`id_subcategoria`, `nome_subcategoria`, `pk_categoria`) VALUES
(1, 'Linguagens', 1),
(2, 'Programas', 1),
(3, 'Computação gráfica', 1),
(4, 'Redes', 1),
(5, 'Sistemas Operacionais', 1),
(6, 'Analise de Sistemas', 1),
(7, 'Banco de dados', 1),
(8, 'Administração Geral', 2),
(9, 'Administração de RH e Terceirização', 2),
(10, 'Administração e Matemática Financeiras', 2),
(11, 'Administração de Vendas e Marketing', 2),
(12, 'Administração de Produção', 2),
(13, 'Estatística', 2),
(14, 'Comércio Exterior', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefone`
--

CREATE TABLE `telefone` (
  `id_telefone` int(11) NOT NULL,
  `ddd` varchar(2) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `tipo_telefone` varchar(20) NOT NULL,
  `fk_cliente` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `telefone`
--

INSERT INTO `telefone` (`id_telefone`, `ddd`, `numero`, `tipo_telefone`, `fk_cliente`) VALUES
(3, '11', '6478644', 'Telefone', 5),
(4, '11', '6478644', 'Celular', 6),
(5, '11', '1646498', 'Celular', 7),
(6, '11', '9498446', 'Celular', 6),
(7, '11', '64643513', 'Telefone', 8),
(8, '11', '64643513', 'Telefone', 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cartao`
--
ALTER TABLE `cartao`
  ADD PRIMARY KEY (`id_cartao`),
  ADD KEY `fk_cartao` (`pk_cliente`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indexes for table `categoria_livro`
--
ALTER TABLE `categoria_livro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`),
  ADD KEY `id_subcategoria` (`id_subcategoria`),
  ADD KEY `id_livro` (`id_livro`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indexes for table `cupom`
--
ALTER TABLE `cupom`
  ADD PRIMARY KEY (`id_cupom`);

--
-- Indexes for table `cupom_pedido`
--
ALTER TABLE `cupom_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pedido` (`fk_pedido`),
  ADD KEY `fk_cupom` (`fk_cupom`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id_endereco`),
  ADD KEY `fk_endereco` (`pk_cliente`);

--
-- Indexes for table `grupoprecificacao`
--
ALTER TABLE `grupoprecificacao`
  ADD PRIMARY KEY (`id_grupo`);

--
-- Indexes for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`id_item_pedido`),
  ADD KEY `fk_livro` (`fk_livro`),
  ADD KEY `PK_PEDIDO` (`PK_PEDIDO`);

--
-- Indexes for table `livros`
--
ALTER TABLE `livros`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pagamento_cartao`
--
ALTER TABLE `pagamento_cartao`
  ADD PRIMARY KEY (`id_pagamento_cartao`),
  ADD KEY `fk_cartao` (`fk_cartao`),
  ADD KEY `fk_pedido` (`fk_pedido`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_endereco` (`fk_endereco`);

--
-- Indexes for table `pedido_troca`
--
ALTER TABLE `pedido_troca`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pedido` (`fk_pedido`),
  ADD KEY `fk_usuario` (`fk_usuario`);

--
-- Indexes for table `pedido_troca_itens`
--
ALTER TABLE `pedido_troca_itens`
  ADD PRIMARY KEY (`id_pedido_troca_itens`),
  ADD KEY `fk_pedido_troca` (`fk_pedido_troca`),
  ADD KEY `fk_livro` (`fk_livro`);

--
-- Indexes for table `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD PRIMARY KEY (`id_subcategoria`);

--
-- Indexes for table `telefone`
--
ALTER TABLE `telefone`
  ADD PRIMARY KEY (`id_telefone`),
  ADD KEY `fk_cliente` (`fk_cliente`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cartao`
--
ALTER TABLE `cartao`
  MODIFY `id_cartao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `categoria_livro`
--
ALTER TABLE `categoria_livro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `cupom`
--
ALTER TABLE `cupom`
  MODIFY `id_cupom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `cupom_pedido`
--
ALTER TABLE `cupom_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id_endereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `grupoprecificacao`
--
ALTER TABLE `grupoprecificacao`
  MODIFY `id_grupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `id_item_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `livros`
--
ALTER TABLE `livros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `pagamento_cartao`
--
ALTER TABLE `pagamento_cartao`
  MODIFY `id_pagamento_cartao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `pedido_troca`
--
ALTER TABLE `pedido_troca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `pedido_troca_itens`
--
ALTER TABLE `pedido_troca_itens`
  MODIFY `id_pedido_troca_itens` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `id_subcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `telefone`
--
ALTER TABLE `telefone`
  MODIFY `id_telefone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
