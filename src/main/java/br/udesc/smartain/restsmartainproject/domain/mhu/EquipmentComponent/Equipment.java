package br.udesc.smartain.restsmartainproject.domain.mhu.EquipmentComponent;

import org.hibernate.annotations.Comment;

import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(schema = "mhu", name = "tbequipamento")
@Comment("Tabela de cadastro dos equipamentos das máquinas")
public class Equipment {

}
