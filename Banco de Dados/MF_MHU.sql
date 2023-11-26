--CREATE SCHEMA mhu;

--CREATE TABLE mhu.tbtipomodelomaquina (
--	tmmcodigo INTEGER NOT NULL,
--	tmmdescricao VARCHAR(250) NOT NULL,
--	CONSTRAINT tbtipomodelomaquina_pkey PRIMARY KEY (tmmcodigo)
--);
--COMMENT ON TABLE mhu.tbtipomodelomaquina IS 'Tabela dos tipos de máquinas';
--COMMENT ON COLUMN mhu.tbtipomodelomaquina.tmmcodigo IS 'Código sequencial do tipo de máquina';
--COMMENT ON COLUMN mhu.tbtipomodelomaquina.tmmdescricao IS 'Descrição do tipo de máquina';

--CREATE TABLE mhu.tbturno (
--	turcodigo INTEGER NOT NULL,
--	turdescri VARCHAR(100) NOT NULL,
--	turstatus SMALLINT NOT NULL,
--	turhoraini VARCHAR(5) NOT NULL,
--	turhorafim VARCHAR(5) NOT NULL,
--	CONSTRAINT tbturno_pkey PRIMARY KEY (turcodigo)
--);
--COMMENT ON TABLE mhu.tbturno IS 'Tabela de cadastros dos turnos de trabalho';
--COMMENT ON COLUMN mhu.tbturno.turcodigo IS 'Código sequencial do turno';
--COMMENT ON COLUMN mhu.tbturno.turdescri IS 'Descrição do turno';
--COMMENT ON COLUMN mhu.tbturno.turstatus IS 'Status do turno (0-Inativo, 1-Ativo)';
--COMMENT ON COLUMN mhu.tbturno.turhoraini IS 'Hora de início do turno (Formato HH:mm)';
--COMMENT ON COLUMN mhu.tbturno.turhorafim IS 'Hora de término do turno (Formato HH:mm)';


--CREATE TABLE mhu.tbcargo (
--	carcodigo INTEGER NOT NULL,
--	carnome VARCHAR(100) NOT NULL,
--	cardescricao VARCHAR(250),
--	carstatus VARCHAR NOT NULL,
--	CONSTRAINT tbcargo_pkey PRIMARY KEY (carcodigo)
--);
--COMMENT ON TABLE mhu.tbcargo IS 'Tabela de cadastro dos cargos dos profissionais';
--COMMENT ON COLUMN mhu.tbcargo.carcodigo IS 'Código sequencial do cargo';
--COMMENT ON COLUMN mhu.tbcargo.carnome IS 'Nome do cargo';
--COMMENT ON COLUMN mhu.tbcargo.cardescricao IS 'Breve descrição do cargo';
--COMMENT ON COLUMN mhu.tbcargo.carstatus IS 'Status do cargo (0-Inativo, 1-Ativo)';


--CREATE TABLE mhu.tbtipounidade (
--	tiucodigo INTEGER NOT NULL,
--	tiucodfaz INTEGER NOT NULL,
--	tiunome VARCHAR(50) NOT NULL,
--	tiudescricao VARCHAR(250) NOT NULL,
--	CONSTRAINT tbtipounidade_pkey PRIMARY KEY (tiucodigo)
--);
--COMMENT ON TABLE mhu.tbtipounidade IS 'Tabela domínio de tipo de unidade';
--COMMENT ON COLUMN mhu.tbtipounidade.tiucodigo IS 'Código sequencial do tipo de unidade';
--COMMENT ON COLUMN mhu.tbtipounidade.tiucodfaz IS 'Código do tipo de unidade de acordo com o ministério da fazenda';
--COMMENT ON COLUMN mhu.tbtipounidade.tiunome IS 'Nome do tipo de unidade';
--COMMENT ON COLUMN mhu.tbtipounidade.tiudescricao IS 'Descrição do tipo de undiade';


--CREATE TABLE mhu.tbmarca (
--	marcodigo INTEGER NOT NULL,
--	marnome VARCHAR(150) NOT NULL,
--	mardatcad DATE NOT NULL,
--	usucodigo INTEGER NOT NULL,
--	CONSTRAINT tbmarca_pkey PRIMARY KEY (marcodigo)
--);
--COMMENT ON TABLE mhu.tbmarca IS 'Tabela de cadastro de marcas';
--COMMENT ON COLUMN mhu.tbmarca.marcodigo IS 'Código sequencial da marca';
--COMMENT ON COLUMN mhu.tbmarca.marnome IS 'Nome da marca';
--COMMENT ON COLUMN mhu.tbmarca.mardatcad IS 'Data de cadastro da marca';
--COMMENT ON COLUMN mhu.tbmarca.usucodigo IS 'Código do usuário de cadastro da marca';


--CREATE TABLE mhu.tbcomponente (
--	comcodigo INTEGER NOT NULL,
--	comnome VARCHAR(250) NOT NULL,
--	comdadostec VARCHAR(1000) NOT NULL,
--	marcodigo INTEGER NOT NULL,
--	CONSTRAINT tbcomponente_pkey PRIMARY KEY (comcodigo)
--);
--COMMENT ON TABLE mhu.tbcomponente IS 'Tabela de cadastro dos componentes das máquinas';
--COMMENT ON COLUMN mhu.tbcomponente.comcodigo IS 'Código sequencial do componente';
--COMMENT ON COLUMN mhu.tbcomponente.comnome IS 'Nome do componente';
--COMMENT ON COLUMN mhu.tbcomponente.comdadostec IS 'Dados e especificações técnicas do componente';
--COMMENT ON COLUMN mhu.tbcomponente.marcodigo IS 'Código sequencial da marca';


CREATE TABLE mhu.tbimportacao (
	impcodigo INTEGER NOT NULL,
	usucodigo INTEGER NOT NULL,
	impdatahora TIMESTAMP NOT NULL,
	CONSTRAINT tbimportacao_pkey PRIMARY KEY (impcodigo)
);
COMMENT ON TABLE mhu.tbimportacao IS 'Tabela de importação dos dados do ERP.';
COMMENT ON COLUMN mhu.tbimportacao.impcodigo IS 'Código sequencial da importação';
COMMENT ON COLUMN mhu.tbimportacao.usucodigo IS 'Código do usuário que realizou a importação';
COMMENT ON COLUMN mhu.tbimportacao.impdatahora IS 'Data/hora da importação';


CREATE TABLE mhu.tbimportacaopessoa (
	ippcodigo INTEGER NOT NULL,
	impcodigo INTEGER NOT NULL,
	ipsinfo VARCHAR,
	CONSTRAINT tbimportacaopessoa_pkey PRIMARY KEY (ippcodigo)
);
COMMENT ON TABLE mhu.tbimportacaopessoa IS 'Tabela de cadastros das pessoas oriundas de integração com ERP.';
COMMENT ON COLUMN mhu.tbimportacaopessoa.ippcodigo IS 'Código (ERP) da pessoa';
COMMENT ON COLUMN mhu.tbimportacaopessoa.impcodigo IS 'Código sequencial da importação';
COMMENT ON COLUMN mhu.tbimportacaopessoa.ipsinfo IS 'Informações da pessoa (JSON ou XML)';


CREATE TABLE mhu.tbimportacaofornecedor (
	imfcodigo INTEGER NOT NULL,
	impcodigo INTEGER NOT NULL,
	ipfinfo VARCHAR NOT NULL,
	CONSTRAINT tbimportacaofornecedor_pkey PRIMARY KEY (imfcodigo)
);
COMMENT ON TABLE mhu.tbimportacaofornecedor IS 'Tabela de cadastros dos fornecedores oriundos de integração com ERP.';
COMMENT ON COLUMN mhu.tbimportacaofornecedor.imfcodigo IS 'Código (ERP) do fornecedor';
COMMENT ON COLUMN mhu.tbimportacaofornecedor.impcodigo IS 'Código sequencial da importação';
COMMENT ON COLUMN mhu.tbimportacaofornecedor.ipfinfo IS 'Informações do fornecedor (JSON ou XML)';


--CREATE TABLE mhu.tbfornecedor (
--	forcodigo INTEGER NOT NULL,
--	forrazaosocial VARCHAR(150) NOT NULL,
--	forcnpj VARCHAR(16) NOT NULL,
--	forcontatotelefone VARCHAR(20),
--	forcontatoemail VARCHAR(100),
--	imfcodigo INTEGER NOT NULL,
--	CONSTRAINT tbfornecedor_pkey PRIMARY KEY (forcodigo)
-- );
--COMMENT ON TABLE mhu.tbfornecedor IS 'Tabela de cadastros dos fornecedores';
--COMMENT ON COLUMN mhu.tbfornecedor.forcodigo IS 'Código sequencial do fornecedor';
--COMMENT ON COLUMN mhu.tbfornecedor.forrazaosocial IS 'Razão social do fornecedor';
--COMMENT ON COLUMN mhu.tbfornecedor.forcnpj IS 'CNPJ do fornecedor';
--COMMENT ON COLUMN mhu.tbfornecedor.forcontatoemail IS 'E-mail de contato do fornecedor';
--COMMENT ON COLUMN mhu.tbfornecedor.forcontatotelefone IS 'Telefone de contato do fornecedor';
--COMMENT ON COLUMN mhu.tbfornecedor.imfcodigo IS 'Código (ERP) do fornecedor';

CREATE TABLE mhu.tbcomponentefornecedor (
	comcodigo INTEGER NOT NULL,
	forcodigo INTEGER NOT NULL,
	CONSTRAINT tbcomponentefornecedor_pkey PRIMARY KEY (comcodigo, forcodigo)
);
COMMENT ON TABLE mhu.tbcomponentefornecedor IS 'Tabela de relacionamento dos componentes com os fornecedores';
COMMENT ON COLUMN mhu.tbcomponentefornecedor.comcodigo IS 'Código do componente';
COMMENT ON COLUMN mhu.tbcomponentefornecedor.forcodigo IS 'Código do fornecedor';


CREATE TABLE mhu.tbimportacaofabricante (
	imfcodigo INTEGER NOT NULL,
	impcodigo INTEGER NOT NULL,
	ipfdados VARCHAR NOT NULL,
	CONSTRAINT tbimportacaofabricante_pkey PRIMARY KEY (imfcodigo)
);
COMMENT ON TABLE mhu.tbimportacaofabricante IS 'Tabela de cadastros dos fabricantes oriundos de integração com ERP.';
COMMENT ON COLUMN mhu.tbimportacaofabricante.imfcodigo IS 'Código (ERP) do fabricante';
COMMENT ON COLUMN mhu.tbimportacaofabricante.impcodigo IS 'Código sequencial da importação';
COMMENT ON COLUMN mhu.tbimportacaofabricante.ipfdados IS 'Dados (JSON) das informações do fabricante';


--CREATE TABLE mhu.tbfabricante (
--	fabcodigo INTEGER NOT NULL,
--	fabrazaosocial VARCHAR(150) NOT NULL,
--	fabcnpj VARCHAR(16) NOT NULL,
--	fabstatus SMALLINT NOT NULL,
--	imfcodigo INTEGER NOT NULL,
--	CONSTRAINT tbfabricante_pkey PRIMARY KEY (fabcodigo)
--);
--COMMENT ON TABLE mhu.tbfabricante IS 'Tabela de cadastro dos fabricantes de máquinas/equipamentos/componentes';
--COMMENT ON COLUMN mhu.tbfabricante.fabcodigo IS 'Código sequencial do fabricante';
--COMMENT ON COLUMN mhu.tbfabricante.fabrazaosocial IS 'Razão social do fabricante';
--COMMENT ON COLUMN mhu.tbfabricante.fabcnpj IS 'CNPJ do fabricante';
--COMMENT ON COLUMN mhu.tbfabricante.fabstatus IS 'Status do fabricante (0-Inativo, 1-Ativo)';
--COMMENT ON COLUMN mhu.tbfabricante.imfcodigo IS 'Código (ERP) do fabricante';


--CREATE TABLE mhu.tbmodelomaquina (
--	momcodigo INTEGER NOT NULL,
--	fabcodigo INTEGER,
--	mommodelo VARCHAR(250) NOT NULL,
--	momdimensoes VARCHAR(250) NOT NULL,
--	tmmcodigo INTEGER NOT NULL,
--	CONSTRAINT tbmodelomaquina_pkey PRIMARY KEY (momcodigo)
--);
--COMMENT ON TABLE mhu.tbmodelomaquina IS 'Tabela de cadastros de modelos de máquinas';
--COMMENT ON COLUMN mhu.tbmodelomaquina.momcodigo IS 'Código sequencial do modelo de máquina';
--COMMENT ON COLUMN mhu.tbmodelomaquina.fabcodigo IS 'Código do fabricante';
--COMMENT ON COLUMN mhu.tbmodelomaquina.mommodelo IS 'Modelo da máquina';
--COMMENT ON COLUMN mhu.tbmodelomaquina.momdimensoes IS 'Dimensões do modelo de máquina';
--COMMENT ON COLUMN mhu.tbmodelomaquina.tmmcodigo IS 'Código sequencial do tipo de máquina';


CREATE TABLE mhu.tbprofissional (
	prfcodigo INTEGER NOT NULL,
	prfnome VARCHAR(150) NOT NULL,
	prfdatnasc DATE,
	prfdatcad TIMESTAMP NOT NULL,
	prfcpf VARCHAR(11) NOT NULL,
	prfstatus VARCHAR NOT NULL,
	usucodigo INTEGER NOT NULL,
	ippcodigo INTEGER NOT NULL,
	CONSTRAINT tbprofissional_pkey PRIMARY KEY (prfcodigo)
);
COMMENT ON TABLE mhu.tbprofissional IS 'Tabela de cadastro dos profissionais';
COMMENT ON COLUMN mhu.tbprofissional.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mhu.tbprofissional.prfnome IS 'Nome do profissional';
COMMENT ON COLUMN mhu.tbprofissional.prfdatnasc IS 'Data de nascimento do profissional';
COMMENT ON COLUMN mhu.tbprofissional.prfdatcad IS 'Data/hora de cadastro do profissional';
COMMENT ON COLUMN mhu.tbprofissional.prfcpf IS 'CPF do profissional';
COMMENT ON COLUMN mhu.tbprofissional.prfstatus IS 'Status do profissional (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbprofissional.usucodigo IS 'Código do usuário atrelado ao profissional';
COMMENT ON COLUMN mhu.tbprofissional.ippcodigo IS 'Código (ERP) da pessoa';


CREATE TABLE mhu.tbunidadefabril (
	unfcodigo INTEGER NOT NULL,
	clicodigo INTEGER NOT NULL,
	tiucodigo INTEGER NOT NULL,
	prfcodigo INTEGER NOT NULL,
	usucodigo INTEGER NOT NULL,
	unftag VARCHAR(2),
	CONSTRAINT tbunidadefabril_pkey PRIMARY KEY (unfcodigo)
);
COMMENT ON TABLE mhu.tbunidadefabril IS 'Tabela de unidades fabris do cliente.';
COMMENT ON COLUMN mhu.tbunidadefabril.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbunidadefabril.clicodigo IS 'Código sequencial do cliente';
COMMENT ON COLUMN mhu.tbunidadefabril.tiucodigo IS 'Código sequencial do tipo de unidade';
COMMENT ON COLUMN mhu.tbunidadefabril.prfcodigo IS 'Código do profissional de manutenção responsável (Gestor)';
COMMENT ON COLUMN mhu.tbunidadefabril.usucodigo IS 'Código sequencial do usuário';
COMMENT ON COLUMN mhu.tbunidadefabril.unftag IS 'Tag da unidade fabril (Ex.: XX)';


CREATE TABLE mhu.tbsetor (
	unfcodigo INTEGER NOT NULL,
	setcodigo INTEGER NOT NULL,
	setnome VARCHAR NOT NULL,
	setdescricao VARCHAR(250) NOT NULL,
	setativo SMALLINT NOT NULL,
	setdatcad TIMESTAMP NOT NULL,
	settag VARCHAR(7),
	CONSTRAINT tbsetor_pkey PRIMARY KEY (unfcodigo, setcodigo)
);
COMMENT ON TABLE mhu.tbsetor IS 'Tabela de cadastro dos setores das unidades fabris.';
COMMENT ON COLUMN mhu.tbsetor.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbsetor.setcodigo IS 'Código sequencial do setor';
COMMENT ON COLUMN mhu.tbsetor.setnome IS 'Nome do setor';
COMMENT ON COLUMN mhu.tbsetor.setdescricao IS 'Descrição das atvididades desempenhadas no setor';
COMMENT ON COLUMN mhu.tbsetor.setativo IS 'Status do setor (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbsetor.setdatcad IS 'Data de cadastro do setor';
COMMENT ON COLUMN mhu.tbsetor.settag IS 'Tag do setor (Ex.: ABC-001)';


CREATE TABLE mhu.tbcelulaproducao (
	clpcodigo INTEGER NOT NULL,
	clpnome VARCHAR NOT NULL,
	clpdescricao VARCHAR(250),
	unfcodigo INTEGER,
	clpativo SMALLINT NOT NULL,
	clpdatcad TIMESTAMP NOT NULL,
	setcodigo INTEGER,
	clptag VARCHAR(7),
	CONSTRAINT tbcelulaproducao_pkey PRIMARY KEY (clpcodigo)
);
COMMENT ON TABLE mhu.tbcelulaproducao IS 'Tabela de células de produção de um setor';
COMMENT ON COLUMN mhu.tbcelulaproducao.clpcodigo IS 'Código sequencial da célula de produção';
COMMENT ON COLUMN mhu.tbcelulaproducao.clpnome IS 'Nome da célula de produção';
COMMENT ON COLUMN mhu.tbcelulaproducao.clpdescricao IS 'Descrição da função da célula de produção';
COMMENT ON COLUMN mhu.tbcelulaproducao.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbcelulaproducao.clpativo IS 'Status da célula de produção (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbcelulaproducao.clpdatcad IS 'Data de cadastro da célula de produção';
COMMENT ON COLUMN mhu.tbcelulaproducao.setcodigo IS 'Código sequencial do setor';
COMMENT ON COLUMN mhu.tbcelulaproducao.clptag IS 'Tag da célula de produção (Ex.: ABC-001) 3 primeiros dígitos livres e 3 últimos dígitos iguais do setor a qual pertence';


CREATE TABLE mhu.tbmaquina (
	maqcodigo INTEGER NOT NULL,
	setcodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	maqdadostec VARCHAR(1000) NOT NULL,
	momcodigo INTEGER NOT NULL,
	clpcodigo INTEGER,
	maqtag VARCHAR(7) NOT NULL,
	maqdataaquisicao DATE NOT NULL,
	maqdatcad TIMESTAMP NOT NULL,
	maqdatexpiragarantia DATE,
	maqgarantia SMALLINT NOT NULL,
	CONSTRAINT tbmaquina_pkey PRIMARY KEY (maqcodigo)
);
COMMENT ON TABLE mhu.tbmaquina IS 'Tabela de cadastros de máquinas dos setores/células de produção';
COMMENT ON COLUMN mhu.tbmaquina.maqcodigo IS 'Código sequencial da máquina';
COMMENT ON COLUMN mhu.tbmaquina.setcodigo IS 'Código sequencial do setor';
COMMENT ON COLUMN mhu.tbmaquina.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbmaquina.maqdadostec IS 'Dados técnicos do modelo de máquina';
COMMENT ON COLUMN mhu.tbmaquina.momcodigo IS 'Código do modelo de máquina';
COMMENT ON COLUMN mhu.tbmaquina.clpcodigo IS 'Código da célula de produção';
COMMENT ON COLUMN mhu.tbmaquina.maqtag IS 'Tag da máquina (Ex.: ABC-001)';
COMMENT ON COLUMN mhu.tbmaquina.maqdataaquisicao IS 'Data de aquisição da máquina';
COMMENT ON COLUMN mhu.tbmaquina.maqdatcad IS 'Data de cadastro da máquina';
COMMENT ON COLUMN mhu.tbmaquina.maqdatexpiragarantia IS 'Data de expiração da garantia da máquina';
COMMENT ON COLUMN mhu.tbmaquina.maqgarantia IS 'Indica de a máquina está na garantia (0-Não, 1-Sim)';


CREATE TABLE mhu.tbmaquinacomponente (
	maqcodigo INTEGER NOT NULL,
	maccodigo INTEGER NOT NULL,
	comcodigo INTEGER NOT NULL,
	macstatus VARCHAR NOT NULL,
	CONSTRAINT tbmaquinacomponente_pkey PRIMARY KEY (maqcodigo, maccodigo, comcodigo)
);
COMMENT ON TABLE mhu.tbmaquinacomponente IS 'Tabela dos componentes das máquinas';
COMMENT ON COLUMN mhu.tbmaquinacomponente.maqcodigo IS 'Código sequencial da máquina';
COMMENT ON COLUMN mhu.tbmaquinacomponente.comcodigo IS 'Código sequencial do componente';
COMMENT ON COLUMN mhu.tbmaquinacomponente.macstatus IS 'Status do componente da máquina (0-Inativo, 1-Ativo)';


CREATE TABLE mhu.tbmaquinamanual (
	maqcodigo INTEGER NOT NULL,
	mqmcodigo INTEGER NOT NULL,
	mqmcaminhoarquivo VARCHAR NOT NULL,
	mqmtipo SMALLINT NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbmaquinamanual_pkey PRIMARY KEY (maqcodigo)
);
COMMENT ON TABLE mhu.tbmaquinamanual IS 'Tabela que armazena os manuais da máquina';
COMMENT ON COLUMN mhu.tbmaquinamanual.maqcodigo IS 'Código sequencial da máquina';
COMMENT ON COLUMN mhu.tbmaquinamanual.mqmcodigo IS 'Código sequencial do manual da máquina';
COMMENT ON COLUMN mhu.tbmaquinamanual.mqmcaminhoarquivo IS 'Caminho do arquivo do manual no servidor';
COMMENT ON COLUMN mhu.tbmaquinamanual.mqmtipo IS 'Tipo de manual (0-Manual de operação, 1-Manual de manutenção)';
COMMENT ON COLUMN mhu.tbmaquinamanual.usucodigo IS 'Código do usuário que cadastrou';


CREATE TABLE mhu.tbequipe (
	eqpcodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	eqpnome VARCHAR(100) NOT NULL,
	prfcodigo INTEGER NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbequipe_pkey PRIMARY KEY (eqpcodigo)
);
COMMENT ON TABLE mhu.tbequipe IS 'Tabela de cadastro das equipes de manutenção';
COMMENT ON COLUMN mhu.tbequipe.eqpcodigo IS 'Código sequencial da equipe';
COMMENT ON COLUMN mhu.tbequipe.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbequipe.eqpnome IS 'Nome da equipe';
COMMENT ON COLUMN mhu.tbequipe.prfcodigo IS 'Código do profissional responsável';
COMMENT ON COLUMN mhu.tbequipe.usucodigo IS 'Código do usuário que cadastrou a equipe';


CREATE TABLE mhu.tbprofissionalvinculo (
	prfcodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	carcodigo INTEGER NOT NULL,
	prvstatus SMALLINT NOT NULL,
	turcodigo INTEGER NOT NULL,
	CONSTRAINT tbprofissionalvinculo_pkey PRIMARY KEY (prfcodigo, unfcodigo)
);
COMMENT ON TABLE mhu.tbprofissionalvinculo IS 'Tabela do vínculo do profissional com unidade';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.carcodigo IS 'Código sequencial do cargo';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.prvstatus IS 'Status do vínculo (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.turcodigo IS 'Código sequencial do turno';


CREATE TABLE mhu.tbequipeprofis (
	unfcodigo INTEGER NOT NULL,
	eqpcodigo INTEGER NOT NULL,
	prfcodigo INTEGER NOT NULL,
	epfstatus SMALLINT NOT NULL,
	CONSTRAINT tbequipeprofis_pkey PRIMARY KEY (unfcodigo, eqpcodigo, prfcodigo)
);
COMMENT ON TABLE mhu.tbequipeprofis IS 'Tabela dos profissionais das equipes';
COMMENT ON COLUMN mhu.tbequipeprofis.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbequipeprofis.eqpcodigo IS 'Código sequencial da equipe';
COMMENT ON COLUMN mhu.tbequipeprofis.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mhu.tbequipeprofis.epfstatus IS 'Status do profissional na equipe (0-Inativo, 1-Ativo)';


ALTER TABLE mhu.tbmodelomaquina ADD CONSTRAINT tbtipomodelomaquina_tbmodelomaquina_fk
FOREIGN KEY (tmmcodigo)
REFERENCES mhu.tbtipomodelomaquina (tmmcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissionalvinculo ADD CONSTRAINT tbturno_tbprofissionalvinculo_fk
FOREIGN KEY (turcodigo)
REFERENCES mhu.tbturno (turcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissionalvinculo ADD CONSTRAINT tbcargo_tbprofissionalvinculo_fk
FOREIGN KEY (carcodigo)
REFERENCES mhu.tbcargo (carcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbunidadefabril ADD CONSTRAINT tbtipounidade_tbunidadefabril_fk
FOREIGN KEY (tiucodigo)
REFERENCES mhu.tbtipounidade (tiucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbunidadefabril ADD CONSTRAINT tbcliente_new_table_fk
FOREIGN KEY (clicodigo)
REFERENCES glo.tbcliente (clicodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissional ADD CONSTRAINT tbusuario_tbprofissional_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbunidadefabril ADD CONSTRAINT tbusuario_tbunidadefabril_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbimportacao ADD CONSTRAINT tbusuario_tbimportacao_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbequipe ADD CONSTRAINT tbusuario_tbequipe_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquinamanual ADD CONSTRAINT tbusuario_tbmaquinamanual_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmarca ADD CONSTRAINT tbusuario_tbmarca_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbcomponente ADD CONSTRAINT tbmarca_tbcomponente_fk
FOREIGN KEY (marcodigo)
REFERENCES mhu.tbmarca (marcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbcomponentefornecedor ADD CONSTRAINT tbcomponente_tbcomponentefornecedor_fk
FOREIGN KEY (comcodigo)
REFERENCES mhu.tbcomponente (comcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquinacomponente ADD CONSTRAINT tbcomponente_tbmaquinacomponente_fk
FOREIGN KEY (comcodigo)
REFERENCES mhu.tbcomponente (comcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbimportacaofabricante ADD CONSTRAINT tbimportacao_tbimportacaofabricante_fk
FOREIGN KEY (impcodigo)
REFERENCES mhu.tbimportacao (impcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbimportacaofornecedor ADD CONSTRAINT tbimportacao_tbimportacaofornecedor_fk
FOREIGN KEY (impcodigo)
REFERENCES mhu.tbimportacao (impcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbimportacaopessoa ADD CONSTRAINT tbimportacao_tbimportacaopessoa_fk
FOREIGN KEY (impcodigo)
REFERENCES mhu.tbimportacao (impcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissional ADD CONSTRAINT tbimportacaopessoa_tbprofissional_fk
FOREIGN KEY (ippcodigo)
REFERENCES mhu.tbimportacaopessoa (ippcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbfornecedor ADD CONSTRAINT tbimportacaofornecedor_tbfornecedor_fk
FOREIGN KEY (imfcodigo)
REFERENCES mhu.tbimportacaofornecedor (imfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbcomponentefornecedor ADD CONSTRAINT tbfornecedor_tbcomponentefornecedor_fk
FOREIGN KEY (forcodigo)
REFERENCES mhu.tbfornecedor (forcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbfabricante ADD CONSTRAINT tbimportacaofabricante_tbfabricante_fk
FOREIGN KEY (imfcodigo)
REFERENCES mhu.tbimportacaofabricante (imfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodelomaquina ADD CONSTRAINT tbfabricante_tbmodelomaquina_fk
FOREIGN KEY (fabcodigo)
REFERENCES mhu.tbfabricante (fabcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquina ADD CONSTRAINT tbmodelomaquina_tbmaquina_fk
FOREIGN KEY (momcodigo)
REFERENCES mhu.tbmodelomaquina (momcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbunidadefabril ADD CONSTRAINT tbprofissional_tbunidadefabril_fk
FOREIGN KEY (prfcodigo)
REFERENCES mhu.tbprofissional (prfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissionalvinculo ADD CONSTRAINT tbprofissional_tbprofissionalvinculo_fk
FOREIGN KEY (prfcodigo)
REFERENCES mhu.tbprofissional (prfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbequipe ADD CONSTRAINT tbprofissional_tbequipe_fk
FOREIGN KEY (prfcodigo)
REFERENCES mhu.tbprofissional (prfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissionalvinculo ADD CONSTRAINT tbunidadefabril_tbprofissionalvinculo_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbequipe ADD CONSTRAINT tbunidadefabril_tbequipe_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbsetor ADD CONSTRAINT tbunidadefabril_tbsetor_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbcelulaproducao ADD CONSTRAINT tbsetor_tbcelulaproducao_fk
FOREIGN KEY (setcodigo, unfcodigo)
REFERENCES mhu.tbsetor (setcodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquina ADD CONSTRAINT tbsetor_tbmaquina_fk
FOREIGN KEY (setcodigo, unfcodigo)
REFERENCES mhu.tbsetor (setcodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquina ADD CONSTRAINT tbcelulaproducao_tbmaquina_fk
FOREIGN KEY (clpcodigo)
REFERENCES mhu.tbcelulaproducao (clpcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquinamanual ADD CONSTRAINT tbmaquina_tbmaquinamanual_fk
FOREIGN KEY (maqcodigo)
REFERENCES mhu.tbmaquina (maqcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquinacomponente ADD CONSTRAINT tbmaquina_tbmaquinacomponente_fk
FOREIGN KEY (maqcodigo)
REFERENCES mhu.tbmaquina (maqcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbequipeprofis ADD CONSTRAINT tbequipe_tbequipeprofis_fk
FOREIGN KEY (eqpcodigo)
REFERENCES mhu.tbequipe (eqpcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbequipeprofis ADD CONSTRAINT tbprofissionalvinculo_tbequipeprofis_fk
FOREIGN KEY (prfcodigo, unfcodigo)
REFERENCES mhu.tbprofissionalvinculo (prfcodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;