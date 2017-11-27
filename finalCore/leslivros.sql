-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 27-Nov-2017 às 00:56
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
(2, '1112223334454', 'MasterCard', '09/2022', '655', 6),
(4, '123124124', 'MasterCard', '09/2022', '667', 7),
(5, '124545', 'MasterCard', '09/2022', '123', 8),
(13, '11122233344550', 'VISA', '01/26', '590', 6);

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
  `cnpj` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `genero`, `nome_cliente`, `dtNascimento`, `cpf`, `email`, `senha`, `status`, `cnpj`) VALUES
(5, 'M', 'Marco', '1965-07-23', '11122233344', 'teste', '123', 1, NULL),
(6, 'M', 'Lucas Julio Alves', '1997-07-25', '11122233344', 'lucasjulio', '123', 1, NULL),
(7, 'M', 'Felipe', '2000-08-23', '4443332221100', 'felipe@teste.com', '123', 1, NULL),
(8, 'M', 'Fabio', '1996-08-01', '55544433322', 'fabio@teste.com', '123', 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cupom`
--

CREATE TABLE `cupom` (
  `id_cupom` int(11) NOT NULL,
  `codigo` varchar(6) NOT NULL,
  `desconto` double(4,2) DEFAULT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cupom`
--

INSERT INTO `cupom` (`id_cupom`, `codigo`, `desconto`, `tipo`) VALUES
(1, 'aaa111', 20.00, 0),
(2, 'bbb222', 30.00, 0),
(3, 'ccc333', 40.00, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cupom_pedido`
--

CREATE TABLE `cupom_pedido` (
  `id` int(11) NOT NULL,
  `fk_cupom` int(11) NOT NULL,
  `fk_pedido` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
(8, 'Casa', 'Teste', 'Teste', '801', 'Teste', '12345520', 'Suzno', 'SP', 'Brasil', 7, 'Casa'),
(9, 'Apartamento', 'Avenida', 'R. Teste', '100', 'Jardim Teste', '08676250', 'Suzano', 'SP', 'Brasil', 6, 'Trabalho'),
(16, 'Apartamento', 'Avenida', 'R. Teste', '1234', 'Jardim Teste', '06676250', 'Mogi', 'SP', 'Brasil', 6, 'Casa'),
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
  `id` int(11) NOT NULL,
  `fk_livro` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double(4,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  `pk_categoria` int(11) NOT NULL,
  `pk_subcategoria` int(11) NOT NULL,
  `pk_grupo` int(11) NOT NULL,
  `qtde_estoque` int(11) DEFAULT NULL,
  `preco_livro` double(4,2) DEFAULT NULL,
  `largura` varchar(6) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `livros`
--

INSERT INTO `livros` (`id`, `nome`, `autor`, `ano`, `status`, `titulo`, `editora`, `edicao`, `isbn`, `num_paginas`, `sinopse`, `altura`, `peso`, `profundidade`, `pk_categoria`, `pk_subcategoria`, `pk_grupo`, `qtde_estoque`, `preco_livro`, `largura`) VALUES
(8, 'Nome', 'Autor', '2017', 1, 'Titulo', 'Editora', '1', '10000', '100', 'Sinopse', 100, 100, 100, 1, 1, 1, 2, 50.10, '10.00'),
(9, 'Nome', 'Autor', '2017', 1, 'Titulo', 'Editora', '1', '654654', '100', '200', 200, 200, 200, 1, 1, 1, 2, 50.20, '11.00'),
(10, 'Nome', 'Autor', '2017', 1, 'Titulo', 'Editora', '1', '654654', '100', '200', 200, 200, 200, 1, 4, 2, 2, 50.30, '12.00'),
(11, 'Dougras', 'Lucas', '2017', 1, 'Dougras', 'Fatec', '1', '12345', '100', 'Sinopse', 200, 200, 200, 1, 7, 1, 2, 1.40, '13.00'),
(12, 'Nudge', 'Richard Thaler', '2009', 1, 'Nudge', 'Penguin Books UK', '1', '9780141040011', '100', 'Sinopse', 19, 13, 2, 2, 8, 2, 2, 50.60, '14.00'),
(13, 'Building Resilience', 'Sandberg, Sheryl / Grant, Adam', '2017', 1, 'Building Resilience', 'Knopf', '1', '9781524711214', '240', 'Livro', 24.1, 0.44, 2.5, 2, 8, 1, 2, 50.80, '15.00'),
(14, 'The Founder\'s Mentality', 'Allen, James / Zook, Chris', '2016', 1, 'The Founder\'s Mentality', 'Harvard Business Review Press', '1', '9781633691162', '224', 'Sinopse', 23.62, 0.41, 2.29, 2, 12, 2, 2, 50.50, '16.00'),
(16, 'Facing Adversity', 'Sandberg, Sheryl / Grant, Adam', '2017', 1, 'Facing Adversity', 'Knopf', '1', '9781524711214', '240', 'Livro', 24.1, 0.44, 2.5, 2, 8, 1, 2, 50.70, '17.00');

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
(7, '11', '64643513', 'Telefone', 8);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_livro` (`fk_livro`);

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
  MODIFY `id_cartao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `cupom`
--
ALTER TABLE `cupom`
  MODIFY `id_cupom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `cupom_pedido`
--
ALTER TABLE `cupom_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id_endereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `grupoprecificacao`
--
ALTER TABLE `grupoprecificacao`
  MODIFY `id_grupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `livros`
--
ALTER TABLE `livros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `pagamento_cartao`
--
ALTER TABLE `pagamento_cartao`
  MODIFY `id_pagamento_cartao` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `id_subcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `telefone`
--
ALTER TABLE `telefone`
  MODIFY `id_telefone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
