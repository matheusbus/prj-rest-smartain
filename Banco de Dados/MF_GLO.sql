CREATE SCHEMA glo;
COMMENT ON SCHEMA glo IS 'Configurações e parametrizações do sistema';

CREATE TABLE glo.tbpais (
                paicodigo INTEGER NOT NULL,
                painome VARCHAR(100) NOT NULL,
                CONSTRAINT tbpais_pkey PRIMARY KEY (paicodigo)
);
COMMENT ON TABLE glo.tbpais IS 'Tabela domínio dos países';
COMMENT ON COLUMN glo.tbpais.paicodigo IS 'Código sequencial do país';
COMMENT ON COLUMN glo.tbpais.painome IS 'Nome do país';

CREATE TABLE glo.tbunifederativa (
                paicodigo INTEGER NOT NULL,
                unfcodigo INTEGER NOT NULL,
                unfnome VARCHAR(100) NOT NULL,
                CONSTRAINT tbunifederativa_pkey PRIMARY KEY (paicodigo, unfcodigo)
);
COMMENT ON TABLE glo.tbunifederativa IS 'Tabela domínio de unidades federativas';
COMMENT ON COLUMN glo.tbunifederativa.paicodigo IS 'Código do país';
COMMENT ON COLUMN glo.tbunifederativa.unfcodigo IS 'Código sequencial da unidade federativa';
COMMENT ON COLUMN glo.tbunifederativa.unfnome IS 'Nome da unidade federativa';

CREATE TABLE glo.tbcidade (
                paicodigo INTEGER NOT NULL,
                unfcodigo INTEGER NOT NULL,
                cidcodigo INTEGER NOT NULL,
                cidnome VARCHAR(100) NOT NULL,
                CONSTRAINT tbcidade_pkey PRIMARY KEY (paicodigo, unfcodigo, cidcodigo)
);
COMMENT ON TABLE glo.tbcidade IS 'Tabela domínio das cidades';
COMMENT ON COLUMN glo.tbcidade.paicodigo IS 'Código do país';
COMMENT ON COLUMN glo.tbcidade.unfcodigo IS 'Código da unidade federativa';
COMMENT ON COLUMN glo.tbcidade.cidcodigo IS 'Código sequencial da cidade';
COMMENT ON COLUMN glo.tbcidade.cidnome IS 'Nome da cidade';

CREATE TABLE glo.tbcliente (
                clicodigo INTEGER NOT NULL,
                clirazaosocial VARCHAR(250) NOT NULL,
                clicnpjprincipal VARCHAR(16) NOT NULL,
                clinomefantasia VARCHAR(250) NOT NULL,
                CONSTRAINT tbcliente_pkey PRIMARY KEY (clicodigo)
);
COMMENT ON TABLE glo.tbcliente IS 'Tabbela das informações do cliente que utiliza o sistema';
COMMENT ON COLUMN glo.tbcliente.clicodigo IS 'Código sequencial do cliente';
COMMENT ON COLUMN glo.tbcliente.clirazaosocial IS 'Razão social do cliente';
COMMENT ON COLUMN glo.tbcliente.clicnpjprincipal IS 'CNPJ principal do cliente';
COMMENT ON COLUMN glo.tbcliente.clinomefantasia IS 'Nome fantasia do cliente (mostrado em teal)';

CREATE TABLE glo.tbunidadefabril (
                ufbcodigo INTEGER NOT NULL,
                clicodigo INTEGER NOT NULL,
                paicodigo INTEGER NOT NULL,
                unfcodigo INTEGER NOT NULL,
                cidcodigo INTEGER NOT NULL,
                ufbendereco VARCHAR(300) NOT NULL,
                CONSTRAINT tbunidadefabril_pkey PRIMARY KEY (tbunidadefabril),
                CONSTRAINT tbunidadefabril_tbcliente_fk FOREIGN KEY (clicodigo)
                    REFERENCES glo.tbcliente (clicodigo),
                CONSTRAINT tbunifederativa_tbcidade_fk (paicodigo, unfcodigo, cidcodigo)
                    REFERENCES glo.tbcidade (paicodigo, unfcodigo, cidcodigo)
);
COMMENT ON TABLE glo.tbunidadefabril IS 'Tabela de unidades fabris do cliente.';
COMMENT ON COLUMN glo.tbunidadefabril.ufbcodigo IS 'Código sequencial da unidade fabril';
COMMENT ON COLUMN glo.tbunidadefabril.clicodigo IS 'Código do cliente';
COMMENT ON COLUMN glo.tbunidadefabril.paicodigo IS 'Código do país';
COMMENT ON COLUMN glo.tbunidadefabril.unfcodigo IS 'Código da unidade federativa';
COMMENT ON COLUMN glo.tbunidadefabril.cidcodigo IS 'Código da cidade';
COMMENT ON COLUMN glo.tbunidadefabril.ufbendereco IS 'Endereço descritivo da unidade';

CREATE TABLE glo.tboperacao (
                opecodigo INTEGER NOT NULL,
                opedescricao VARCHAR(100) NOT NULL,
                CONSTRAINT tboperacao_pkey PRIMARY KEY (opecodigo)
);
COMMENT ON TABLE glo.tboperacao IS 'Tabela de domínio das operações aplicadas aos dados.';
COMMENT ON COLUMN glo.tboperacao.opecodigo IS 'Código sequencial da operação';
COMMENT ON COLUMN glo.tboperacao.opedescricao IS 'Descrição da operação';

CREATE TABLE glo.tbtipoprivilegio (
                tipcodigo INTEGER NOT NULL,
                tipdescricao VARCHAR(250) NOT NULL,
                CONSTRAINT tbtipoprivilegio_pkey PRIMARY KEY (tipcodigo)
);
COMMENT ON TABLE glo.tbtipoprivilegio IS 'Tabela de domínio dos tipos de privilégios aplicados no sistema';
COMMENT ON COLUMN glo.tbtipoprivilegio.tipcodigo IS 'Código sequencial do tipo de privilégio';
COMMENT ON COLUMN glo.tbtipoprivilegio.tipdescricao IS 'Descrição do tipo de privilégio';


CREATE TABLE glo.tbsistema (
                siscodigo INTEGER NOT NULL,
                sisnome VARCHAR(100) NOT NULL,
                sisativo SMALLINT NOT NULL,
                clicodigo INTEGER NOT NULL,
                CONSTRAINT tbsistema_pkey PRIMARY KEY (siscodigo)
);
COMMENT ON TABLE glo.tbsistema IS 'Tabela de domínio do sistema';
COMMENT ON COLUMN glo.tbsistema.siscodigo IS 'Código sequencial do sistema';
COMMENT ON COLUMN glo.tbsistema.sisnome IS 'Nome do sistema';
COMMENT ON COLUMN glo.tbsistema.sisativo IS 'Status do sistema (0- Inativo, 1- Ativo)';
COMMENT ON COLUMN glo.tbsistema.clicodigo IS 'Código do cliente';


CREATE TABLE glo.tbsistemaconfiguracao (
                siscodigo INTEGER NOT NULL,
                sispermitemanutfimsemana SMALLINT NOT NULL,
                CONSTRAINT tbsistemaconfiguracao_pkey PRIMARY KEY (siscodigo)
);
COMMENT ON TABLE glo.tbsistemaconfiguracao IS 'Tabela de configuração dos parâmetros globais do sistema.';
COMMENT ON COLUMN glo.tbsistemaconfiguracao.siscodigo IS 'Código do sistema';
COMMENT ON COLUMN glo.tbsistemaconfiguracao.sispermitemanutfimsemana IS 'Sistema permite gerar ordens de manutenção com datas para os finais de semana (0 - Não, 1 - Sábado e Domingo, 2 - Somente sábado, 3 - Somente domingo)';


CREATE TABLE glo.tbmodulo (
                modcodigo INTEGER NOT NULL,
                siscodigo INTEGER NOT NULL,
                modsigla VARCHAR(3) NOT NULL,
                modnome VARCHAR(100) NOT NULL,
                modativo SMALLINT NOT NULL,
                CONSTRAINT tbmodulo_pkey PRIMARY KEY (modcodigo, siscodigo)
);
COMMENT ON TABLE glo.tbmodulo IS 'Tabela de domínio dos módulos disponíveis no sistema.';
COMMENT ON COLUMN glo.tbmodulo.modcodigo IS 'Código sequencial do módulo';
COMMENT ON COLUMN glo.tbmodulo.siscodigo IS 'Código do sistema';
COMMENT ON COLUMN glo.tbmodulo.modsigla IS 'Sigla do módulo';
COMMENT ON COLUMN glo.tbmodulo.modnome IS 'Nome do módulo';
COMMENT ON COLUMN glo.tbmodulo.modativo IS 'Status do módulo (0- Inativo, 1- Ativo)';


CREATE TABLE glo.tbsistemamanutencao (
                simcodigo INTEGER NOT NULL,
                siscodigo INTEGER NOT NULL,
                simativo SMALLINT NOT NULL,
                simdatinicio TIMESTAMP NOT NULL,
                simdatafim TIMESTAMP NOT NULL,
                CONSTRAINT tbsistemamanutencao_pkey PRIMARY KEY (simcodigo, siscodigo)
);
COMMENT ON TABLE glo.tbsistemamanutencao IS 'Tabela de manutenções do sistema (log de releases)';
COMMENT ON COLUMN glo.tbsistemamanutencao.simcodigo IS 'Código sequencial da manutenção';
COMMENT ON COLUMN glo.tbsistemamanutencao.siscodigo IS 'Código do sistema';
COMMENT ON COLUMN glo.tbsistemamanutencao.simativo IS 'Status da manutenção (0- Inativo, 1- Ativo)';
COMMENT ON COLUMN glo.tbsistemamanutencao.simdatinicio IS 'Data/hora de início da manutenção';
COMMENT ON COLUMN glo.tbsistemamanutencao.simdatafim IS 'Data/hora fim da manutenção';


CREATE TABLE glo.tbrotina (
                rotcodigo INTEGER NOT NULL,
                modcodigo INTEGER NOT NULL,
                siscodigo INTEGER NOT NULL,
                rotnome VARCHAR(100) NOT NULL,
                rotcaminho VARCHAR(250) NOT NULL,
                rotdescricao VARCHAR(300) NOT NULL,
                rotativo SMALLINT NOT NULL,
                CONSTRAINT tbrotina_pkey PRIMARY KEY (rotcodigo, modcodigo, siscodigo)
);
COMMENT ON TABLE glo.tbrotina IS 'Tabela de rotinas presentes em cada módulo do sistema.';
COMMENT ON COLUMN glo.tbrotina.rotcodigo IS 'Código sequencial da rotina';
COMMENT ON COLUMN glo.tbrotina.modcodigo IS 'Código do módulo';
COMMENT ON COLUMN glo.tbrotina.siscodigo IS 'Código do sistema';
COMMENT ON COLUMN glo.tbrotina.rotnome IS 'Nome da rotina';
COMMENT ON COLUMN glo.tbrotina.rotcaminho IS 'Caminho (path) da rotina';
COMMENT ON COLUMN glo.tbrotina.rotdescricao IS 'Descrição da rotina';
COMMENT ON COLUMN glo.tbrotina.rotativo IS 'Status da rotina (0- Inativo, 1- Ativo)';


CREATE TABLE glo.tbprivilegio (
                tipcodigo INTEGER NOT NULL,
                rotcodigo INTEGER NOT NULL,
                modcodigo INTEGER NOT NULL,
                siscodigo INTEGER NOT NULL,
                priativo SMALLINT NOT NULL,
                CONSTRAINT tbprivilegio_pkey PRIMARY KEY (tipcodigo, rotcodigo, modcodigo, siscodigo)
);
COMMENT ON TABLE glo.tbprivilegio IS 'Tabela de domínio relacionamento das rotinas com tipos de privilégio';
COMMENT ON COLUMN glo.tbprivilegio.tipcodigo IS 'Código do tipo de privilégio';
COMMENT ON COLUMN glo.tbprivilegio.rotcodigo IS 'Código da rotina';
COMMENT ON COLUMN glo.tbprivilegio.modcodigo IS 'Código do módulo';
COMMENT ON COLUMN glo.tbprivilegio.siscodigo IS 'Código do sistema';
COMMENT ON COLUMN glo.tbprivilegio.priativo IS 'Status do privilégio (0- Inativo, 1- Ativo)';


CREATE TABLE glo.tbgrupousuario (
                grucodigo INTEGER NOT NULL,
                grunome VARCHAR(100) NOT NULL,
                grudescricao VARCHAR(300) NOT NULL,
                gruativo SMALLINT NOT NULL,
                CONSTRAINT grupousuario_pkey PRIMARY KEY (grucodigo)
);
COMMENT ON TABLE glo.tbgrupousuario IS 'Tabela de domínio dos usuários do sistema';
COMMENT ON COLUMN glo.tbgrupousuario.grucodigo IS 'Código sequencial do grupo';
COMMENT ON COLUMN glo.tbgrupousuario.grunome IS 'Nome do grupo';
COMMENT ON COLUMN glo.tbgrupousuario.grudescricao IS 'Descrição do grupo';
COMMENT ON COLUMN glo.tbgrupousuario.gruativo IS 'Status do grupo (0- Inativo, 1- Ativo)';


CREATE TABLE glo.tbgrupoacesso (
                grucodigo INTEGER NOT NULL,
                tipcodigo INTEGER NOT NULL,
                rotcodigo INTEGER NOT NULL,
                modcodigo INTEGER NOT NULL,
                siscodigo INTEGER NOT NULL,
                graativo SMALLINT NOT NULL,
                CONSTRAINT tbgrupoacesso_pkey PRIMARY KEY (grucodigo, tipcodigo, rotcodigo, modcodigo, siscodigo)
);
COMMENT ON TABLE glo.tbgrupoacesso IS 'Tabela de relacionamento dos grupos de usuários com os privilégios de sistema';
COMMENT ON COLUMN glo.tbgrupoacesso.grucodigo IS 'Código do grupo';
COMMENT ON COLUMN glo.tbgrupoacesso.tipcodigo IS 'Código do tipo de privilégio';
COMMENT ON COLUMN glo.tbgrupoacesso.rotcodigo IS 'Código da rotina';
COMMENT ON COLUMN glo.tbgrupoacesso.modcodigo IS 'Código do módulo';
COMMENT ON COLUMN glo.tbgrupoacesso.siscodigo IS 'Código do sistema';
COMMENT ON COLUMN glo.tbgrupoacesso.graativo IS 'Status do vínculo grupo x privilégio (0- Inativo, 1- Ativo)';


CREATE TABLE glo.tbusuario (
                usucodigo INTEGER NOT NULL,
                grucodigo INTEGER NOT NULL,
                usulogin VARCHAR(30) NOT NULL,
                ususenha VARCHAR(32) NOT NULL,
                usudatcad TIMESTAMP NOT NULL,
                usudatalt TIMESTAMP,
                usuativo SMALLINT NOT NULL,
                CONSTRAINT tbusuario_pkey PRIMARY KEY (usucodigo)
);
COMMENT ON TABLE glo.tbusuario IS 'Tabela que armazena informações dos usuários do sistema';
COMMENT ON COLUMN glo.tbusuario.usucodigo IS 'Código sequencial do usuário';
COMMENT ON COLUMN glo.tbusuario.grucodigo IS 'Código do grupo do usuário';
COMMENT ON COLUMN glo.tbusuario.usulogin IS 'Login do usuário';
COMMENT ON COLUMN glo.tbusuario.ususenha IS 'Senha do usuário';
COMMENT ON COLUMN glo.tbusuario.usudatcad IS 'Data de cadastro do usuário';
COMMENT ON COLUMN glo.tbusuario.usudatalt IS 'Data da última alteração do usuário';
COMMENT ON COLUMN glo.tbusuario.usuativo IS 'Status do usuário (0- Inativo, 1- Ativo)';


CREATE TABLE glo.tbmonitoracao (
                mtrcodigo BIGINT NOT NULL,
                usucodigo INTEGER NOT NULL,
                mtrdathora TIMESTAMP NOT NULL,
                opecodigo INTEGER NOT NULL,
                mrtdescricao VARCHAR(1000) NOT NULL,
                rotcodigo INTEGER NOT NULL,
                modcodigo INTEGER NOT NULL,
                siscodigo INTEGER NOT NULL,
                CONSTRAINT tbmonitoracao_pkey PRIMARY KEY (mtrcodigo)
);
COMMENT ON COLUMN glo.tbmonitoracao.mtrcodigo IS 'Código sequencial da monitoração';
COMMENT ON COLUMN glo.tbmonitoracao.usucodigo IS 'Código do usuário';
COMMENT ON COLUMN glo.tbmonitoracao.mtrdathora IS 'Data/hora em que foi realizada a monitoração';
COMMENT ON COLUMN glo.tbmonitoracao.opecodigo IS 'Código da operação';
COMMENT ON COLUMN glo.tbmonitoracao.mrtdescricao IS 'Descritivo da monitoração';
COMMENT ON COLUMN glo.tbmonitoracao.rotcodigo IS 'Código da rotina';
COMMENT ON COLUMN glo.tbmonitoracao.modcodigo IS 'Código do módulo';
COMMENT ON COLUMN glo.tbmonitoracao.siscodigo IS 'Código do sistema';


ALTER TABLE glo.tbunifederativa ADD CONSTRAINT tbpais_tbunifederativa_fk
FOREIGN KEY (paicodigo)
REFERENCES glo.tbpais (paicodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbcidade ADD CONSTRAINT tbunifederativa_tbcidade_fk
FOREIGN KEY (paicodigo, unfcodigo)
REFERENCES glo.tbunifederativa (paicodigo, unfcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbunidadefabril ADD CONSTRAINT tbcidade_tbunidadefabril_fk
FOREIGN KEY (paicodigo, unfcodigo, cidcodigo)
REFERENCES glo.tbcidade (paicodigo, unfcodigo, cidcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbsistema ADD CONSTRAINT tbcliente_tbsistema_fk
FOREIGN KEY (clicodigo)
REFERENCES glo.tbcliente (clicodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbunidadefabril ADD CONSTRAINT tbcliente_new_table_fk
FOREIGN KEY (clicodigo)
REFERENCES glo.tbcliente (clicodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbmonitoracao ADD CONSTRAINT tboperacao_tbmonitoracao_fk
FOREIGN KEY (opecodigo)
REFERENCES glo.tboperacao (opecodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbprivilegio ADD CONSTRAINT glo_tbtipoprivilegio_glo_tbprivilegio_fk
FOREIGN KEY (tipcodigo)
REFERENCES glo.tbtipoprivilegio (tipcodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbsistemamanutencao ADD CONSTRAINT glo_tbsistema_glo_tbsistemamanutencao_fk
FOREIGN KEY (siscodigo)
REFERENCES glo.tbsistema (siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbmodulo ADD CONSTRAINT glo_tbsistema_glo_tbmodulo_fk
FOREIGN KEY (siscodigo)
REFERENCES glo.tbsistema (siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbsistemaconfiguracao ADD CONSTRAINT glo_tbsistema_glo_tbsistemaconfiguracao_fk
FOREIGN KEY (siscodigo)
REFERENCES glo.tbsistema (siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbrotina ADD CONSTRAINT glo_tbmodulo_glo_tbrotina_fk
FOREIGN KEY (modcodigo, siscodigo)
REFERENCES glo.tbmodulo (modcodigo, siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbprivilegio ADD CONSTRAINT glo_tbrotina_glo_tbprivilegio_fk
FOREIGN KEY (rotcodigo, modcodigo, siscodigo)
REFERENCES glo.tbrotina (rotcodigo, modcodigo, siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbmonitoracao ADD CONSTRAINT tbrotina_tbmonitoracao_fk
FOREIGN KEY (rotcodigo, modcodigo, siscodigo)
REFERENCES glo.tbrotina (rotcodigo, modcodigo, siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbgrupoacesso ADD CONSTRAINT glo_tbprivilegio_glo_tbgrupoacesso_fk
FOREIGN KEY (tipcodigo, rotcodigo, modcodigo, siscodigo)
REFERENCES glo.tbprivilegio (tipcodigo, rotcodigo, modcodigo, siscodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbusuario ADD CONSTRAINT _glo_tbgrupousuario____glo_tbusuario_
FOREIGN KEY (grucodigo)
REFERENCES glo.tbgrupousuario (grucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbgrupoacesso ADD CONSTRAINT glo_tbgrupousuario_glo_tbgrupoacesso_fk
FOREIGN KEY (grucodigo)
REFERENCES glo.tbgrupousuario (grucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE glo.tbmonitoracao ADD CONSTRAINT tbusuario_tbmonitoracao_fk
FOREIGN KEY (usucodigo)
REFERENCES glo.tbusuario (usucodigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;