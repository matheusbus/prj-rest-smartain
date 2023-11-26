CREATE SCHEMA mhu;
COMMENT ON SCHEMA mhu IS 'Máquinas, homens e unidades';

CREATE SCHEMA mpp;
COMMENT ON SCHEMA mpp is 'Manutenção programada e planejada'

--CREATE TABLE mpp.tbservico (
--	sercodigo SERIAL NOT NULL,
--	tpiintervencao VARCHAR(50) NOT NULL,
--	tpisigla VARCHAR NOT NULL,
--	tpidescricao VARCHAR(500) NOT NULL,
--	CONSTRAINT tbtipointervencao_pkey PRIMARY KEY (sercodigo)
--);
--COMMENT ON TABLE mpp.tbservico IS 'Tabela dos serviços de intervenção';
--COMMENT ON COLUMN mpp.tbservico.sercodigo IS 'Código sequencial do serviço de intervenção';
--COMMENT ON COLUMN mpp.tbservico.tpiintervencao IS 'Nome da intervenção';
--COMMENT ON COLUMN mpp.tbservico.tpisigla IS 'Sigla do tipo de intervenção';
--COMMENT ON COLUMN mpp.tbservico.tpidescricao IS 'Descrição do tipo de intervenção';


--CREATE TABLE mpp.tbsintomaservico (
--	stscodigo SERIAL NOT NULL,
--	stssintoma VARCHAR(50) NOT NULL,
--	stssigla VARCHAR(3) NOT NULL,
--	stsdescricao VARCHAR(500) NOT NULL,
--	CONSTRAINT tbsintomaservico_pkey PRIMARY KEY (stscodigo)
--);
--COMMENT ON TABLE mpp.tbsintomaservico IS 'Tabela dos sintomas para geração de serviços';
--COMMENT ON COLUMN mpp.tbsintomaservico.stscodigo IS 'Código sequencial do sintoma de manutenção';
--COMMENT ON COLUMN mpp.tbsintomaservico.stssintoma IS 'Sintoma de manutenção';
--COMMENT ON COLUMN mpp.tbsintomaservico.stssigla IS 'Sigla do sintoma de manutenção';
--COMMENT ON COLUMN mpp.tbsintomaservico.stsdescricao IS 'Descrição do sintoma de manutenção';


--CREATE TABLE mpp.tbtipomanutencao (
--	tpmcodigo INTEGER NOT NULL,
--	tpmdescricao VARCHAR(250) NOT NULL,
--	CONSTRAINT tbtipomanutencao_pkey PRIMARY KEY (tpmcodigo)
--);
--COMMENT ON TABLE mpp.tbtipomanutencao IS 'Tabela que armazena os tipos de manutenção';
--COMMENT ON COLUMN mpp.tbtipomanutencao.tpmcodigo IS 'Código sequencial do tipo de manutenção';
--COMMENT ON COLUMN mpp.tbtipomanutencao.tpmdescricao IS 'Descrição do tipo de manutenção';


--CREATE TABLE mpp.tbcausaservico (
--	csscodigo SERIAL NOT NULL,
--	csscausa VARCHAR(50) NOT NULL,
--	csssigla VARCHAR(3) NOT NULL,
--	cssdescricao VARCHAR(500) NOT NULL,
--	CONSTRAINT tbcausaservico_pkey PRIMARY KEY (csscodigo)
--);
--COMMENT ON TABLE mpp.tbcausaservico IS 'Tabela que armazena as causas de necessidade de manutenção';
--COMMENT ON COLUMN mpp.tbcausaservico.csscodigo IS 'Código sequencial da causa de manutenção';
--COMMENT ON COLUMN mpp.tbcausaservico.csscausa IS 'Causa de manutenção';
--COMMENT ON COLUMN mpp.tbcausaservico.csssigla IS 'Sigla da causa de manutenção';
--COMMENT ON COLUMN mpp.tbcausaservico.cssdescricao IS 'Descrição da causa de manutenção';


CREATE TABLE mpp.tbtipogeraorgem (
	tgocodigo INTEGER NOT NULL,
	tgodescricao VARCHAR NOT NULL,
	CONSTRAINT tbtipogeraorgem_pkey PRIMARY KEY (tgocodigo)
);
COMMENT ON TABLE mpp.tbtipogeraorgem IS 'Tabela dos tipos de geração de ordem de manutenção';
COMMENT ON COLUMN mpp.tbtipogeraorgem.tgocodigo IS 'Código sequencial do tipo de geração de ordem de serviço';
COMMENT ON COLUMN mpp.tbtipogeraorgem.tgodescricao IS 'Descrição do tipo de geração de ordem de serviço';

--CREATE TABLE mpp.tbservicoprioridade (
--	svpcodigo INTEGER NOT NULL,
--	svpdescricao VARCHAR(200) NOT NULL,
--	CONSTRAINT tbservicoprioridade_pkey PRIMARY KEY (svpcodigo)
--);
--COMMENT ON TABLE mpp.tbservicoprioridade IS 'Tabela de domínio das prioridades de servicos de manutencao';
--COMMENT ON COLUMN mpp.tbservicoprioridade.svpcodigo IS 'Codigo sequencial da prioridade';
--COMMENT ON COLUMN mpp.tbservicoprioridade.svpdescricao IS 'Descricao da prioridade';


CREATE TABLE mhu.tbturno (
	turcodigo SERIAL NOT NULL,
	turdescri VARCHAR(100) NOT NULL,
	turstatus SMALLINT NOT NULL,
	turhoraini VARCHAR(5) NOT NULL,
	turhorafim VARCHAR(5) NOT NULL,
	CONSTRAINT tbturno_pkey PRIMARY KEY (turcodigo)
);
COMMENT ON TABLE mhu.tbturno IS 'Tabela de cadastros dos turnos de trabalho';
COMMENT ON COLUMN mhu.tbturno.turcodigo IS 'Código sequencial do turno';
COMMENT ON COLUMN mhu.tbturno.turdescri IS 'Descrição do turno';
COMMENT ON COLUMN mhu.tbturno.turstatus IS 'Status do turno (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbturno.turhoraini IS 'Hora de início do turno (Formato HH:mm)';
COMMENT ON COLUMN mhu.tbturno.turhorafim IS 'Hora de término do turno (Formato HH:mm)';


CREATE TABLE mhu.tbcargo (
	carcodigo INTEGER NOT NULL,
	carnome VARCHAR(100) NOT NULL,
	cardescricao VARCHAR(250),
	carstatus VARCHAR NOT NULL,
	CONSTRAINT tbcargo_pkey PRIMARY KEY (carcodigo)
);
COMMENT ON TABLE mhu.tbcargo IS 'Tabela de cadastro dos cargos dos profissionais';
COMMENT ON COLUMN mhu.tbcargo.carcodigo IS 'Código sequencial do cargo';
COMMENT ON COLUMN mhu.tbcargo.carnome IS 'Nome do cargo';
COMMENT ON COLUMN mhu.tbcargo.cardescricao IS 'Breve descrição do cargo';
COMMENT ON COLUMN mhu.tbcargo.carstatus IS 'Status do cargo (0-Inativo, 1-Ativo)';


CREATE TABLE mhu.tbtipounidade (
	tiucodigo INTEGER NOT NULL,
	tiucodfaz INTEGER NOT NULL,
	tiunome VARCHAR(50) NOT NULL,
	tiudescricao VARCHAR(250) NOT NULL,
	CONSTRAINT tbtipounidade_pkey PRIMARY KEY (tiucodigo)
);
COMMENT ON TABLE mhu.tbtipounidade IS 'Tabela domínio de tipo de unidade';
COMMENT ON COLUMN mhu.tbtipounidade.tiucodigo IS 'Código sequencial do tipo de unidade';
COMMENT ON COLUMN mhu.tbtipounidade.tiucodfaz IS 'Código do tipo de unidade de acordo com o ministério da fazenda';
COMMENT ON COLUMN mhu.tbtipounidade.tiunome IS 'Nome do tipo de unidade';
COMMENT ON COLUMN mhu.tbtipounidade.tiudescricao IS 'Descrição do tipo de undiade';

CREATE TABLE mhu.tbtipomodeloequipamento (
	tmecodigo INTEGER NOT NULL,
	tmedescricao VARCHAR(250) NOT NULL,
	tmedatcad VARCHAR NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbtipomodeloequipamento_pkey PRIMARY KEY (tmecodigo)
);
COMMENT ON TABLE mhu.tbtipomodeloequipamento IS 'Tabela de cadastro de tipos de modelos de equipamentos';
COMMENT ON COLUMN mhu.tbtipomodeloequipamento.tmecodigo IS 'Código sequencial do tipo de modelo de equipamento';
COMMENT ON COLUMN mhu.tbtipomodeloequipamento.tmedescricao IS 'Descrição do tipo de modelo de equipamento';
COMMENT ON COLUMN mhu.tbtipomodeloequipamento.tmedatcad IS 'Data de cadastro do tipo de modelo de equipamento';
COMMENT ON COLUMN mhu.tbtipomodeloequipamento.usucodigo IS 'Código sequencial do usuário';


CREATE TABLE mhu.tbtipomodelomaquina (
	tmmcodigo INTEGER NOT NULL,
	tmmdescricao VARCHAR(250) NOT NULL,
	tmmdatcad DATE NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbtipomodelomaquina_pkey PRIMARY KEY (tmmcodigo)
);
COMMENT ON TABLE mhu.tbtipomodelomaquina IS 'Tabela dos tipos de máquinas';
COMMENT ON COLUMN mhu.tbtipomodelomaquina.tmmcodigo IS 'Código sequencial do tipo de máquina';
COMMENT ON COLUMN mhu.tbtipomodelomaquina.tmmdescricao IS 'Descrição do tipo de máquina';
COMMENT ON COLUMN mhu.tbtipomodelomaquina.tmmdatcad IS 'Data de cadastro do tipo de modelo de maquina';
COMMENT ON COLUMN mhu.tbtipomodelomaquina.usucodigo IS 'Código do usuário de cadastro';


CREATE TABLE mhu.tbtipomodelocomponente (
	tmccodigo INTEGER NOT NULL,
	tmcdescricao VARCHAR(250) NOT NULL,
	tmcdatcad TIMESTAMP NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbtipomodelocomponente_pkey PRIMARY KEY (tmccodigo)
);
COMMENT ON TABLE mhu.tbtipomodelocomponente IS 'Tabela de cadastro de tipos de modelos de componentes';
COMMENT ON COLUMN mhu.tbtipomodelocomponente.tmccodigo IS 'Codigo sequencial do tipo de modelo de componente';
COMMENT ON COLUMN mhu.tbtipomodelocomponente.tmcdescricao IS 'Descricao do tipo de modelo de componente';
COMMENT ON COLUMN mhu.tbtipomodelocomponente.tmcdatcad IS 'Data de cadastro do tipo de modelo de componente';
COMMENT ON COLUMN mhu.tbtipomodelocomponente.usucodigo IS 'Código do usuário de cadastro';


CREATE TABLE mhu.tbmarca (
	marcodigo SERIAL NOT NULL,
	marnome VARCHAR(150) NOT NULL,
	mardatcad TIMESTAMP NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbmarca_pkey PRIMARY KEY (marcodigo)
);
COMMENT ON TABLE mhu.tbmarca IS 'Tabela de cadastro de marcas';
COMMENT ON COLUMN mhu.tbmarca.marcodigo IS 'Código sequencial da marca';
COMMENT ON COLUMN mhu.tbmarca.marnome IS 'Nome da marca';
COMMENT ON COLUMN mhu.tbmarca.mardatcad IS 'Data de cadastro da marca';
COMMENT ON COLUMN mhu.tbmarca.usucodigo IS 'Código do usuário de cadastro';

CREATE SCHEMA mie;
COMMENT ON SCHEMA mie IS 'Manutenção integrada ao ERP';

CREATE TABLE mie.tbimportacao (
	impcodigo INTEGER NOT NULL,
	usucodigo INTEGER NOT NULL,
	impdatahora TIMESTAMP NOT NULL,
	CONSTRAINT tbimportacao_pkey PRIMARY KEY (impcodigo)
);
COMMENT ON TABLE mie.tbimportacao IS 'Tabela de importação dos dados do ERP.';
COMMENT ON COLUMN mie.tbimportacao.impcodigo IS 'Código sequencial da importação';
COMMENT ON COLUMN mie.tbimportacao.usucodigo IS 'Código do usuário que realizou a importação';
COMMENT ON COLUMN mie.tbimportacao.impdatahora IS 'Data/hora da importação';

CREATE TABLE mie.tbimportacaopessoa (
	ippcodigo INTEGER NOT NULL,
	impcodigo INTEGER NOT NULL,
	ipsinfo VARCHAR,
	CONSTRAINT tbimportacaopessoa_pkey PRIMARY KEY (ippcodigo)
);
COMMENT ON TABLE mie.tbimportacaopessoa IS 'Tabela de cadastros das pessoas oriundas de integração com ERP.';
COMMENT ON COLUMN mie.tbimportacaopessoa.ippcodigo IS 'Código (ERP) da pessoa';
COMMENT ON COLUMN mie.tbimportacaopessoa.impcodigo IS 'Código da importação';
COMMENT ON COLUMN mie.tbimportacaopessoa.ipsinfo IS 'Informações da pessoa (JSON ou XML)';


CREATE TABLE mie.tbimportacaofornecedor (
	imfcodigo INTEGER NOT NULL,
	impcodigo INTEGER NOT NULL,
	ipfinfo VARCHAR NOT NULL,
	CONSTRAINT tbimportacaofornecedor_pkey PRIMARY KEY (imfcodigo)
);
COMMENT ON TABLE mie.tbimportacaofornecedor IS 'Tabela de cadastros dos fornecedores oriundos de integração com ERP.';
COMMENT ON COLUMN mie.tbimportacaofornecedor.imfcodigo IS 'Código (ERP) do fornecedor';
COMMENT ON COLUMN mie.tbimportacaofornecedor.impcodigo IS 'Código da importação';
COMMENT ON COLUMN mie.tbimportacaofornecedor.ipfinfo IS 'Informações do fornecedor (JSON ou XML)';

CREATE TABLE mie.tbimportacaofabricante (
	imfcodigo INTEGER NOT NULL,
	impcodigo INTEGER NOT NULL,
	ipfdados VARCHAR NOT NULL,
	CONSTRAINT tbimportacaofabricante_pkey PRIMARY KEY (imfcodigo)
);
COMMENT ON TABLE mie.tbimportacaofabricante IS 'Tabela de cadastros dos fabricantes oriundos de integração com ERP.';
COMMENT ON COLUMN mie.tbimportacaofabricante.imfcodigo IS 'Código (ERP) do fabricante';
COMMENT ON COLUMN mie.tbimportacaofabricante.impcodigo IS 'Código sequencial da importação';
COMMENT ON COLUMN mie.tbimportacaofabricante.ipfdados IS 'Dados (JSON) das informações do fabricante';


CREATE TABLE mhu.tbfornecedor (
	forcodigo INTEGER NOT NULL,
	forrazaosocial VARCHAR(150) NOT NULL,
	forcnpj VARCHAR(16) NOT NULL,
	forcontatotelefone VARCHAR(20),
	forcontatoemail VARCHAR(100),
	imfcodigo INTEGER NOT NULL,
	CONSTRAINT tbfornecedor_pkey PRIMARY KEY (forcodigo)
);
COMMENT ON TABLE mhu.tbfornecedor IS 'Tabela de cadastros dos fornecedores';
COMMENT ON COLUMN mhu.tbfornecedor.forcodigo IS 'Código sequencial do fornecedor';
COMMENT ON COLUMN mhu.tbfornecedor.forrazaosocial IS 'Razão social do fornecedor';
COMMENT ON COLUMN mhu.tbfornecedor.forcnpj IS 'CNPJ do fornecedor';
COMMENT ON COLUMN mhu.tbfornecedor.forcontatotelefone IS 'Telefone de contato do fornecedor';
COMMENT ON COLUMN mhu.tbfornecedor.forcontatoemail IS 'E-mail de contato do fornecedor';
COMMENT ON COLUMN mhu.tbfornecedor.imfcodigo IS 'Código (ERP) do fornecedor';


CREATE TABLE mhu.tbmodeloitem (
	moicodigo INTEGER NOT NULL,
	marcodigo INTEGER NOT NULL,
	forcodigo INTEGER NOT NULL,
	moimodelo VARCHAR(250) NOT NULL,
	tmccodigo INTEGER,
	tmecodigo INTEGER,
	moidatcad TIMESTAMP NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT mhu_tbmodeloitem_pkey PRIMARY KEY (moicodigo)
);
COMMENT ON TABLE mhu.tbmodeloitem IS 'Tabela de cadastro de modelos de itens de máquinas';
COMMENT ON COLUMN mhu.tbmodeloitem.moicodigo IS 'Codigo sequencial do modelo de item';
COMMENT ON COLUMN mhu.tbmodeloitem.marcodigo IS 'Código sequencial da marca';
COMMENT ON COLUMN mhu.tbmodeloitem.forcodigo IS 'Código sequencial do fornecedor';
COMMENT ON COLUMN mhu.tbmodeloitem.moimodelo IS 'Descricao do modelo de item';
COMMENT ON COLUMN mhu.tbmodeloitem.tmccodigo IS 'Codigo do tipo de modelo de componente (quando modelo for de componente)';
COMMENT ON COLUMN mhu.tbmodeloitem.tmecodigo IS 'Código do tipo de modelo de equipamento (quando modelo for de equipamento)';
COMMENT ON COLUMN mhu.tbmodeloitem.moidatcad IS 'Data de cadastro do modelo de item';
COMMENT ON COLUMN mhu.tbmodeloitem.usucodigo IS 'Código do usuário de cadastro';


CREATE TABLE mhu.tbitem (
	itecodigo INTEGER NOT NULL,
	itedadostec VARCHAR(1000) NOT NULL,
	marcodigo INTEGER NOT NULL,
	moicodigo INTEGER NOT NULL,
	itetag VARCHAR(7) NOT NULL,
	forcodigo INTEGER NOT NULL,
	CONSTRAINT tbcomponente_pkey PRIMARY KEY (itecodigo)
);
COMMENT ON TABLE mhu.tbitem IS 'Tabela de cadastro dos itens das máquinas';
COMMENT ON COLUMN mhu.tbitem.itecodigo IS 'Código sequencial do item';
COMMENT ON COLUMN mhu.tbitem.itedadostec IS 'Dados e especificações técnicas do item';
COMMENT ON COLUMN mhu.tbitem.marcodigo IS 'Código da marca';
COMMENT ON COLUMN mhu.tbitem.moicodigo IS 'Codigo do modelo de item';
COMMENT ON COLUMN mhu.tbitem.itetag IS 'Tag do item (Ex.: ABC-001)';
COMMENT ON COLUMN mhu.tbitem.forcodigo IS 'Código do fornecedor';

CREATE TABLE mhu.tbfabricante (
	fabcodigo INTEGER NOT NULL,
	fabrazaosocial VARCHAR(150) NOT NULL,
	fabcnpj VARCHAR(16) NOT NULL,
	fabstatus SMALLINT NOT NULL,
	imfcodigo INTEGER NOT NULL,
	CONSTRAINT tbfabricante_pkey PRIMARY KEY (fabcodigo)
);
COMMENT ON TABLE mhu.tbfabricante IS 'Tabela de cadastro dos fabricantes de máquinas/equipamentos/componentes';
COMMENT ON COLUMN mhu.tbfabricante.fabcodigo IS 'Código sequencial do fabricante';
COMMENT ON COLUMN mhu.tbfabricante.fabrazaosocial IS 'Razão social do fabricante';
COMMENT ON COLUMN mhu.tbfabricante.fabcnpj IS 'CNPJ do fabricante';
COMMENT ON COLUMN mhu.tbfabricante.fabstatus IS 'Status do fabricante (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbfabricante.imfcodigo IS 'Código (ERP) do fabricante';


CREATE TABLE mhu.tbmodelomaquina (
	momcodigo INTEGER NOT NULL,
	fabcodigo INTEGER,
	mommodelo VARCHAR(250) NOT NULL,
	momdimensoes VARCHAR(250) NOT NULL,
	tmmcodigo INTEGER NOT NULL,
	momespecific VARCHAR(500) NOT NULL,
	momdatcad DATE NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbmodelomaquina_pkey PRIMARY KEY (momcodigo)
);
COMMENT ON TABLE mhu.tbmodelomaquina IS 'Tabela de cadastros de modelos de máquinas';
COMMENT ON COLUMN mhu.tbmodelomaquina.momcodigo IS 'Código sequencial do modelo de máquina';
COMMENT ON COLUMN mhu.tbmodelomaquina.fabcodigo IS 'Código do fabricante';
COMMENT ON COLUMN mhu.tbmodelomaquina.mommodelo IS 'Modelo da máquina';
COMMENT ON COLUMN mhu.tbmodelomaquina.momdimensoes IS 'Dimensões do modelo de máquina';
COMMENT ON COLUMN mhu.tbmodelomaquina.tmmcodigo IS 'Código sequencial do tipo de máquina';
COMMENT ON COLUMN mhu.tbmodelomaquina.momespecific IS 'Especificacoes tecnicas do modelo de maquina';
COMMENT ON COLUMN mhu.tbmodelomaquina.momdatcad IS 'Data de cadastro do modelo de maquina';
COMMENT ON COLUMN mhu.tbmodelomaquina.usucodigo IS 'Código do usuário de cadastro';


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


CREATE TABLE mpp.tbunidadecronograma (
	unfcodigo INTEGER NOT NULL,
	uncano INTEGER NOT NULL,
	CONSTRAINT tbunidadecronograma_pkey PRIMARY KEY (unfcodigo)
);
COMMENT ON TABLE mpp.tbunidadecronograma IS 'Tabela que armazena os cronogramas de manutenção da unidade fabril';
COMMENT ON COLUMN mpp.tbunidadecronograma.unfcodigo IS 'Código da unidade fabril';
COMMENT ON COLUMN mpp.tbunidadecronograma.uncano IS 'Ano do cronograma';


CREATE TABLE mhu.tbferramenta (
	frmcodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	frmdescricao VARCHAR(250) NOT NULL,
	frmstatus SMALLINT NOT NULL,
	marcodigo INTEGER,
	frmdatcad TIMESTAMP NOT NULL,
	usucodigo INTEGER NOT NULL,
	CONSTRAINT tbferramenta_pkey PRIMARY KEY (frmcodigo, unfcodigo)
);
COMMENT ON TABLE mhu.tbferramenta IS 'Tabela de cadastro das ferramentas';
COMMENT ON COLUMN mhu.tbferramenta.frmcodigo IS 'Código sequencial da ferramenta';
COMMENT ON COLUMN mhu.tbferramenta.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbferramenta.frmdescricao IS 'Descrição da ferramenta';
COMMENT ON COLUMN mhu.tbferramenta.frmstatus IS 'Status da ferramenta (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbferramenta.marcodigo IS 'Código da marca';
COMMENT ON COLUMN mhu.tbferramenta.frmdatcad IS 'Data de cadastro da ferramenta';
COMMENT ON COLUMN mhu.tbferramenta.usucodigo IS 'Código sequencial do usuário';


CREATE TABLE mpp.tbplanomanutencao (
	plmcodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	plmstatus SMALLINT NOT NULL,
	prfcodigo INTEGER NOT NULL,
	plmdatcad TIMESTAMP NOT NULL,
	CONSTRAINT tbplanomanutencao_pkey PRIMARY KEY (plmcodigo, unfcodigo)
);
COMMENT ON TABLE mpp.tbplanomanutencao IS 'Tabela que armazena os planos de manutencao';
COMMENT ON COLUMN mpp.tbplanomanutencao.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mpp.tbplanomanutencao.plmstatus IS 'Status do plano de manutenção (1-Não iniciado, 2-Em progresso, 3-Finalizado)';
COMMENT ON COLUMN mpp.tbplanomanutencao.prfcodigo IS 'Código do profissional responsável pelo plano de manutenção';
COMMENT ON COLUMN mpp.tbplanomanutencao.plmdatcad IS 'Data de cadastro/geração do plano de manutenção';


CREATE TABLE mhu.tbsetor (
	unfcodigo INTEGER NOT NULL,
	setcodigo INTEGER NOT NULL,
	setnome VARCHAR NOT NULL,
	setdescricao VARCHAR(250) NOT NULL,
	setstatus SMALLINT NOT NULL,
	setdatcad TIMESTAMP NOT NULL,
	settag VARCHAR(7),
	CONSTRAINT tbsetor_pkey PRIMARY KEY (unfcodigo, setcodigo)
);
COMMENT ON TABLE mhu.tbsetor IS 'Tabela de cadastro dos setores das unidades fabris.';
COMMENT ON COLUMN mhu.tbsetor.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbsetor.setcodigo IS 'Código sequencial do setor';
COMMENT ON COLUMN mhu.tbsetor.setnome IS 'Nome do setor';
COMMENT ON COLUMN mhu.tbsetor.setdescricao IS 'Descrição das atvididades desempenhadas no setor';
COMMENT ON COLUMN mhu.tbsetor.setstatus IS 'Status do setor (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbsetor.setdatcad IS 'Data de cadastro do setor';
COMMENT ON COLUMN mhu.tbsetor.settag IS 'Tag do setor (Ex.: ABC-001)';


CREATE TABLE mhu.tbcelulaproducao (
	clpcodigo INTEGER NOT NULL,
	clpnome VARCHAR NOT NULL,
	clpdescricao VARCHAR(250),
	unfcodigo INTEGER,
	clpstatus SMALLINT NOT NULL,
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
COMMENT ON COLUMN mhu.tbcelulaproducao.clpstatus IS 'Status da célula de produção (0-Inativo, 1-Ativo)';
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
	usucodigo INTEGER NOT NULL,
	maqstatus SMALLINT NOT NULL,
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
COMMENT ON COLUMN mhu.tbmaquina.usucodigo IS 'Código do usuário que cadastrou a maquina';
COMMENT ON COLUMN mhu.tbmaquina.maqstatus IS 'Status da máquina (0-Inativa, 1-Ativa)';


CREATE TABLE mpp.tbservicosolicitacao (
	srscodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	maqcodigo INTEGER NOT NULL,
	prfcodigo INTEGER NOT NULL,
	srsstatus SMALLINT NOT NULL,
	srsdescricao VARCHAR(500) NOT NULL,
	srsdatabr DATE NOT NULL,
	svpcodigo INTEGER NOT NULL,
	stscodigo INTEGER NOT NULL,
	tpmcodigo INTEGER NOT NULL,
	CONSTRAINT tbservicosolicitacao_pkey PRIMARY KEY (srscodigo, unfcodigo)
);
COMMENT ON TABLE mpp.tbservicosolicitacao IS 'Tabela de solicitacao de servicos de manutencao';
COMMENT ON COLUMN mpp.tbservicosolicitacao.srscodigo IS 'Codigo sequencial da solicitacao';
COMMENT ON COLUMN mpp.tbservicosolicitacao.unfcodigo IS 'Código da unidade fabril';
COMMENT ON COLUMN mpp.tbservicosolicitacao.maqcodigo IS 'Código sequencial da máquina';
COMMENT ON COLUMN mpp.tbservicosolicitacao.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mpp.tbservicosolicitacao.srsstatus IS 'Status da solicitacao (0-Aberta, 1-Aprovada, 2-Negada)';
COMMENT ON COLUMN mpp.tbservicosolicitacao.srsdescricao IS 'Descrição do Serviço e/ou Efeito constatado';
COMMENT ON COLUMN mpp.tbservicosolicitacao.srsdatabr IS 'Data de abertura da solicitação';
COMMENT ON COLUMN mpp.tbservicosolicitacao.svpcodigo IS 'Codigo sequencial da prioridade';
COMMENT ON COLUMN mpp.tbservicosolicitacao.stscodigo IS 'Código sequencial do sintoma de manutenção';
COMMENT ON COLUMN mpp.tbservicosolicitacao.tpmcodigo IS 'Código sequencial do tipo de manutenção';


CREATE TABLE mpp.tbservicoordem (
	svocodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	srscodigo INTEGER,
	maqcodigo INTEGER NOT NULL,
	svodatabr TIMESTAMP NOT NULL,
	prfcodigo INTEGER,
	svpcodigo INTEGER NOT NULL,
	tgocodigo INTEGER NOT NULL,
	svostatus SMALLINT NOT NULL,
	sercodigo INTEGER,
	svoduracaoestimada TIME NOT NULL,
	plmcodigo INTEGER,
	tpmcodigo INTEGER NOT NULL,
	csscodigo INTEGER NOT NULL,
	CONSTRAINT tbservicoordem_pkey PRIMARY KEY (svocodigo, unfcodigo)
);
COMMENT ON TABLE mpp.tbservicoordem IS 'Tabela de ordens de serviços de manutencao';
COMMENT ON COLUMN mpp.tbservicoordem.svocodigo IS 'Código sequencial da ordem de serviço';
COMMENT ON COLUMN mpp.tbservicoordem.unfcodigo IS 'Código da unidade fabril';
COMMENT ON COLUMN mpp.tbservicoordem.srscodigo IS 'Codigo da solicitacao que gerou a ordem';
COMMENT ON COLUMN mpp.tbservicoordem.maqcodigo IS 'Código da máquina';
COMMENT ON COLUMN mpp.tbservicoordem.svodatabr IS 'Data de abertura da ordem de manuteção';
COMMENT ON COLUMN mpp.tbservicoordem.prfcodigo IS 'Código do profissional de abertura';
COMMENT ON COLUMN mpp.tbservicoordem.svpcodigo IS 'Codigo sequencial da prioridade';
COMMENT ON COLUMN mpp.tbservicoordem.tgocodigo IS 'Código do tipo de geração de ordem de serviço';
COMMENT ON COLUMN mpp.tbservicoordem.svostatus IS 'Status da ordem de serviço (0-Não iniciada, 1-Programada, 2-Iniciada, 3-Suspensa, 4-Encerrada)';
COMMENT ON COLUMN mpp.tbservicoordem.sercodigo IS 'Código do tipo de intervenção (Quando encerrada)';
COMMENT ON COLUMN mpp.tbservicoordem.svoduracaoestimada IS 'Duração estimada de realização da ordem de serviço';
COMMENT ON COLUMN mpp.tbservicoordem.plmcodigo IS 'Código do plano de manutencao (caso ordem tenha sido gerada a partir de um)';
COMMENT ON COLUMN mpp.tbservicoordem.tpmcodigo IS 'Código sequencial do tipo de manutenção';
COMMENT ON COLUMN mpp.tbservicoordem.csscodigo IS 'Código sequencial da causa de manutenção';


CREATE TABLE mpp.tbunidadecronogramaitem (
	unfcodigo INTEGER NOT NULL,
	ucicodigo INTEGER NOT NULL,
	ucidatahora TIMESTAMP NOT NULL,
	uciduracaoestimada TIME NOT NULL,
	ucidescricao VARCHAR(250) NOT NULL,
	svocodigo INTEGER NOT NULL,
	CONSTRAINT tbunidadecronogramaitem_pkey PRIMARY KEY (unfcodigo, ucicodigo)
);
COMMENT ON TABLE mpp.tbunidadecronogramaitem IS 'Tabela dos itens do cronograma de manutenção da unidade fabril';
COMMENT ON COLUMN mpp.tbunidadecronogramaitem.unfcodigo IS 'Código da unidade fabril';
COMMENT ON COLUMN mpp.tbunidadecronogramaitem.ucicodigo IS 'Código sequencial do item do cronograma';
COMMENT ON COLUMN mpp.tbunidadecronogramaitem.ucidatahora IS 'Data/hora da programação de execução';
COMMENT ON COLUMN mpp.tbunidadecronogramaitem.uciduracaoestimada IS 'Duração estimada de execução do item do cronograma';
COMMENT ON COLUMN mpp.tbunidadecronogramaitem.ucidescricao IS 'Descrição do evento';
COMMENT ON COLUMN mpp.tbunidadecronogramaitem.svocodigo IS 'Código sequencial da ordem de serviço';


CREATE TABLE mpp.tbservicoordemferramenta (
	svocodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	frmcodigo INTEGER NOT NULL,
	CONSTRAINT tbservicoordemferramenta_pkey PRIMARY KEY (svocodigo, unfcodigo, frmcodigo)
);
COMMENT ON TABLE mpp.tbservicoordemferramenta IS 'Tabela de relacionamento das ferramentas necessárias na ordem de serviço';
COMMENT ON COLUMN mpp.tbservicoordemferramenta.svocodigo IS 'Código da ordem de serviço';
COMMENT ON COLUMN mpp.tbservicoordemferramenta.unfcodigo IS 'Código da unidade fabril';
COMMENT ON COLUMN mpp.tbservicoordemferramenta.frmcodigo IS 'Código da ferramenta';


CREATE TABLE mhu.tbmaquinaitem (
	maqcodigo INTEGER NOT NULL,
	maicodigo INTEGER NOT NULL,
	itecodigo INTEGER NOT NULL,
	macstatus SMALLINT NOT NULL,
	macquantidade INTEGER NOT NULL,
	CONSTRAINT mhu_tbmaquinaitem_pkey PRIMARY KEY (maqcodigo, maicodigo, itecodigo)
);
COMMENT ON TABLE mhu.tbmaquinaitem IS 'Tabela dos itens das máquinas';
COMMENT ON COLUMN mhu.tbmaquinaitem.maqcodigo IS 'Código sequencial da máquina';
COMMENT ON COLUMN mhu.tbmaquinaitem.maicodigo IS 'Código sequencial do item da máquina';
COMMENT ON COLUMN mhu.tbmaquinaitem.itecodigo IS 'Código do componente (quando item for componente)';
COMMENT ON COLUMN mhu.tbmaquinaitem.macstatus IS 'Status do item da máquina (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbmaquinaitem.macquantidade IS 'Quantidade do item na máquina';


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
	CONSTRAINT tbprofissionalvinculo_pkey PRIMARY KEY (prfcodigo, unfcodigo)
);
COMMENT ON TABLE mhu.tbprofissionalvinculo IS 'Tabela do vínculo do profissional com unidade';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.carcodigo IS 'Código sequencial do cargo';
COMMENT ON COLUMN mhu.tbprofissionalvinculo.prvstatus IS 'Status do vínculo (0-Inativo, 1-Ativo)';


CREATE TABLE mpp.tbservicoordemprofis (
	svocodigo INTEGER NOT NULL,
	unfcodigo INTEGER NOT NULL,
	prfcodigo INTEGER NOT NULL,
	sopcodigo INTEGER NOT NULL,
	sopdatainicio DATE NOT NULL,
	sophorainicio TIME NOT NULL,
	sopdatafim DATE NOT NULL,
	sophorafim TIME NOT NULL,
	CONSTRAINT tbservicoordemprofis_pkey PRIMARY KEY (svocodigo, unfcodigo, prfcodigo, sopcodigo)
);
COMMENT ON TABLE mpp.tbservicoordemprofis IS 'Tabela que relaciona a ordem de manutenção com os profissionais das equipes responsáveis';
COMMENT ON COLUMN mpp.tbservicoordemprofis.svocodigo IS 'Código sequencial da ordem de serviço';
COMMENT ON COLUMN mpp.tbservicoordemprofis.unfcodigo IS 'Código da unidade fabril';
COMMENT ON COLUMN mpp.tbservicoordemprofis.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mpp.tbservicoordemprofis.sopcodigo IS 'Sequencial do horário da execução e término';
COMMENT ON COLUMN mpp.tbservicoordemprofis.sopdatainicio IS 'Data de inicio da execução';
COMMENT ON COLUMN mpp.tbservicoordemprofis.sophorainicio IS 'Hora de início da execução';
COMMENT ON COLUMN mpp.tbservicoordemprofis.sopdatafim IS 'Data de fim da execução';
COMMENT ON COLUMN mpp.tbservicoordemprofis.sophorafim IS 'Hora final da execução';


CREATE TABLE mhu.tbequipeprofis (
	unfcodigo INTEGER NOT NULL,
	eqpcodigo INTEGER NOT NULL,
	prfcodigo INTEGER NOT NULL,
	epfstatus SMALLINT NOT NULL,
	turcodigo INTEGER NOT NULL,
	CONSTRAINT tbequipeprofis_pkey PRIMARY KEY (unfcodigo, eqpcodigo, prfcodigo)
);
COMMENT ON TABLE mhu.tbequipeprofis IS 'Tabela dos profissionais das equipes';
COMMENT ON COLUMN mhu.tbequipeprofis.unfcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN mhu.tbequipeprofis.eqpcodigo IS 'Código sequencial da equipe';
COMMENT ON COLUMN mhu.tbequipeprofis.prfcodigo IS 'Código sequencial do profissional';
COMMENT ON COLUMN mhu.tbequipeprofis.epfstatus IS 'Status do profissional na equipe (0-Inativo, 1-Ativo)';
COMMENT ON COLUMN mhu.tbequipeprofis.turcodigo IS 'Código do turno';


ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbtipointervencao_mpp_tbservicoordem_fk
FOREIGN KEY (sercodigo)
REFERENCES mpp.tbservico (sercodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicosolicitacao ADD CONSTRAINT mpp_tbsintomaservico_mpp_tbservicosolicitacao_fk
FOREIGN KEY (stscodigo)
REFERENCES mpp.tbsintomaservico (stscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbtipomanutencao_mpp_tbservicoordem_fk
FOREIGN KEY (tpmcodigo)
REFERENCES mpp.tbtipomanutencao (tpmcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicosolicitacao ADD CONSTRAINT mpp_tbtipomanutencao_mpp_tbservicosolicitacao_fk
FOREIGN KEY (tpmcodigo)
REFERENCES mpp.tbtipomanutencao (tpmcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbcausaservico_mpp_tbservicoordem_fk
FOREIGN KEY (csscodigo)
REFERENCES mpp.tbcausaservico (csscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbtipogeraorgem_mpp_tbservicoordem_fk
FOREIGN KEY (tgocodigo)
REFERENCES mpp.tbtipogeraorgem (tgocodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicosolicitacao ADD CONSTRAINT mpp_tbservicoprioridade_mpp_tbservicosolicitacao_fk
FOREIGN KEY (svpcodigo)
REFERENCES mpp.tbservicoprioridade (svpcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbservicoprioridade_mpp_tbservicoordem_fk
FOREIGN KEY (svpcodigo)
REFERENCES mpp.tbservicoprioridade (svpcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbequipeprofis ADD CONSTRAINT mhu_tbturno_mhu_tbequipeprofis_fk
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

ALTER TABLE mhu.tbunidadefabril ADD CONSTRAINT tbcliente_tbunidadefabril_fk
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

ALTER TABLE mie.tbimportacao ADD CONSTRAINT tbusuario_tbimportacao_fk
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

ALTER TABLE mhu.tbmaquina ADD CONSTRAINT tbusuario_mhu_tbmaquina_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbtipomodelocomponente ADD CONSTRAINT tbusuario_mhu_tbtipomodelocomponente_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodeloitem ADD CONSTRAINT tbusuario_mhu_tbmodelocomponente_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbtipomodelomaquina ADD CONSTRAINT tbusuario_mhu_tbtipomodelomaquina_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodelomaquina ADD CONSTRAINT tbusuario_mhu_tbmodelomaquina_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbferramenta ADD CONSTRAINT tbusuario_mhu_tbferramenta_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbtipomodeloequipamento ADD CONSTRAINT tbusuario_mhu_tbtipomodeloequipamento_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodeloitem ADD CONSTRAINT mhu_tbtipomodeloequipamento_mhu_tbmodeloitem_fk
FOREIGN KEY (tmecodigo)
REFERENCES mhu.tbtipomodeloequipamento (tmecodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodelomaquina ADD CONSTRAINT tbtipomodelomaquina_tbmodelomaquina_fk
FOREIGN KEY (tmmcodigo)
REFERENCES mhu.tbtipomodelomaquina (tmmcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodeloitem ADD CONSTRAINT mhu_tbtipomodelocomponente_mhu_tbmodelocomponente_fk
FOREIGN KEY (tmccodigo)
REFERENCES mhu.tbtipomodelocomponente (tmccodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbitem ADD CONSTRAINT tbmarca_tbcomponente_fk
FOREIGN KEY (marcodigo)
REFERENCES mhu.tbmarca (marcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodeloitem ADD CONSTRAINT mhu_tbmarca_mhu_tbmodelocomponente_fk
FOREIGN KEY (marcodigo)
REFERENCES mhu.tbmarca (marcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbferramenta ADD CONSTRAINT mhu_tbmarca_mhu_tbferramenta_fk
FOREIGN KEY (marcodigo)
REFERENCES mhu.tbmarca (marcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mie.tbimportacaofabricante ADD CONSTRAINT tbimportacao_tbimportacaofabricante_fk
FOREIGN KEY (impcodigo)
REFERENCES mie.tbimportacao (impcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mie.tbimportacaofornecedor ADD CONSTRAINT tbimportacao_tbimportacaofornecedor_fk
FOREIGN KEY (impcodigo)
REFERENCES mie.tbimportacao (impcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mie.tbimportacaopessoa ADD CONSTRAINT tbimportacao_tbimportacaopessoa_fk
FOREIGN KEY (impcodigo)
REFERENCES mie.tbimportacao (impcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbprofissional ADD CONSTRAINT tbimportacaopessoa_tbprofissional_fk
FOREIGN KEY (ippcodigo)
REFERENCES mie.tbimportacaopessoa (ippcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbfornecedor ADD CONSTRAINT tbimportacaofornecedor_tbfornecedor_fk
FOREIGN KEY (imfcodigo)
REFERENCES mie.tbimportacaofornecedor (imfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmodeloitem ADD CONSTRAINT mhu_tbfornecedor_mhu_tbmodelocomponente_fk
FOREIGN KEY (forcodigo)
REFERENCES mhu.tbfornecedor (forcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbitem ADD CONSTRAINT mhu_tbfornecedor_mhu_tbitem_fk
FOREIGN KEY (forcodigo)
REFERENCES mhu.tbfornecedor (forcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbitem ADD CONSTRAINT mhu_tbmodelocomponente_mhu_tbcomponente_fk
FOREIGN KEY (moicodigo)
REFERENCES mhu.tbmodeloitem (moicodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbmaquinaitem ADD CONSTRAINT mhu_tbcomponente_mhu_tbmaquinaitem_fk
FOREIGN KEY (itecodigo)
REFERENCES mhu.tbitem (itecodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbfabricante ADD CONSTRAINT tbimportacaofabricante_tbfabricante_fk
FOREIGN KEY (imfcodigo)
REFERENCES mie.tbimportacaofabricante (imfcodigo)
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

ALTER TABLE mpp.tbservicosolicitacao ADD CONSTRAINT mhu_tbprofissional_mpp_tbservicosolicitacao_fk
FOREIGN KEY (prfcodigo)
REFERENCES mhu.tbprofissional (prfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mhu_tbprofissional_mpp_tbservicoordem_fk
FOREIGN KEY (prfcodigo)
REFERENCES mhu.tbprofissional (prfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbplanomanutencao ADD CONSTRAINT mhu_tbprofissional_mpp_tbplanomanutencao_fk
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

ALTER TABLE mpp.tbservicosolicitacao ADD CONSTRAINT tbunidadefabril_mpp_tbservicosolicitacao_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mhu_tbunidadefabril_mpp_tbservicoordem_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbplanomanutencao ADD CONSTRAINT mhu_tbunidadefabril_tbplanomanutencao_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mhu.tbferramenta ADD CONSTRAINT mhu_tbunidadefabril_mhu_tbferramenta_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbunidadecronograma ADD CONSTRAINT mhu_tbunidadefabril_mpp_tbcronograma_fk
FOREIGN KEY (unfcodigo)
REFERENCES mhu.tbunidadefabril (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbunidadecronogramaitem ADD CONSTRAINT mpp_tbunidadecronograma_mpp_tbunidadecronogramaitem_fk
FOREIGN KEY (unfcodigo)
REFERENCES mpp.tbunidadecronograma (unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordemferramenta ADD CONSTRAINT mhu_tbferramenta_mpp_tbservicoordemferramenta_fk
FOREIGN KEY (frmcodigo, unfcodigo)
REFERENCES mhu.tbferramenta (frmcodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbplanomanutencao_mpp_tbservicoordem_fk
FOREIGN KEY (plmcodigo, unfcodigo)
REFERENCES mpp.tbplanomanutencao (plmcodigo, unfcodigo)
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

ALTER TABLE mhu.tbmaquinaitem ADD CONSTRAINT tbmaquina_tbmaquinacomponente_fk
FOREIGN KEY (maqcodigo)
REFERENCES mhu.tbmaquina (maqcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicosolicitacao ADD CONSTRAINT tbmaquina_mpp_tbservicosolicitacao_fk
FOREIGN KEY (maqcodigo)
REFERENCES mhu.tbmaquina (maqcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mhu_tbmaquina_mpp_tbservicoordem_fk
FOREIGN KEY (maqcodigo)
REFERENCES mhu.tbmaquina (maqcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordem ADD CONSTRAINT mpp_tbservicosolicitacao_mpp_tbservicoordem_fk
FOREIGN KEY (srscodigo, unfcodigo)
REFERENCES mpp.tbservicosolicitacao (srscodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordemferramenta ADD CONSTRAINT mpp_tbservicoordem_mpp_tbservicoordemferramenta_fk
FOREIGN KEY (svocodigo, unfcodigo)
REFERENCES mpp.tbservicoordem (svocodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbservicoordemprofis ADD CONSTRAINT mpp_tbservicoordem_mpp_tbservicoordemequipe_fk
FOREIGN KEY (svocodigo, unfcodigo)
REFERENCES mpp.tbservicoordem (svocodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE mpp.tbunidadecronogramaitem ADD CONSTRAINT mpp_tbservicoordem_mpp_tbunidadecronogramaitem_fk
FOREIGN KEY (svocodigo, unfcodigo)
REFERENCES mpp.tbservicoordem (svocodigo, unfcodigo)
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

ALTER TABLE mpp.tbservicoordemprofis ADD CONSTRAINT mhu_tbprofissionalvinculo_mpp_tbservicoordemprofis_fk
FOREIGN KEY (prfcodigo, unfcodigo)
REFERENCES mhu.tbprofissionalvinculo (prfcodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;