package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.BrandService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent.Component;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent.ComponentRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentComponent.ComponentService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent.ComponentModel;
import br.udesc.smartain.restsmartainproject.domain.mhu.ComponentModelComponent.ComponentModelService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/component")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ComponentModelService componentModelService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Component>> findAll() {
        List<Component> component = componentService.findAll();

        if(component.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(component);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Component> findById(@PathVariable Integer id) {
        Optional<Component> component = componentService.findById(id);

        if(component.isEmpty()) {
            throw new NotFoundException("Component id not found - " + id);
        }

        return ResponseEntity.ok(component.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Component>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Component> components = componentService.findAllByStatus(status);
        if(components.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(components);
    }

    @PostMapping
    public ResponseEntity<Component> createComponent(@Valid @RequestBody ComponentRequest request) {
        Component newComponent = new Component();
        Machine machine = machineService.findById(request.getMachineId()).get();
        Brand brand = brandService.findById(request.getBrandId()).get();
        ComponentModel componentModel = componentModelService.findById(request.getComponentModelId()).get();

        newComponent.setName(request.getName());
        newComponent.setMachine(machine);
        newComponent.setComponentModel(componentModel);
        newComponent.setStatus(RegisterState.valueOf(request.getStatus()));
        newComponent.setTechnicalData(request.getTechnicalData());
        newComponent.setBrand(brand);

        Component savedComponent = componentService.save(newComponent);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedComponent.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Component> updateComponent(@Valid @RequestBody ComponentRequest request, @PathVariable Integer id) {
        Optional<Component> componentToUpdate = componentService.findById(id);
        Machine machine = machineService.findById(request.getMachineId()).get();
        Brand brand = brandService.findById(request.getBrandId()).get();
        ComponentModel componentModel = componentModelService.findById(request.getComponentModelId()).get();

        if(componentToUpdate.isEmpty()) {
            throw new NotFoundException("Component id not found - " + id);
        }

        componentToUpdate = componentToUpdate.map((componentUpdated) -> {
            componentUpdated.setName(request.getName());
            componentUpdated.setMachine(machine);
            componentUpdated.setStatus(RegisterState.valueOf(request.getStatus()));
            componentUpdated.setTechnicalData(request.getTechnicalData());
            componentUpdated.setComponentModel(componentModel);
            componentUpdated.setBrand(brand);
            return componentUpdated;
        });

        return ResponseEntity.ok(componentService.save(componentToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Component> component = componentService.findById(id);

        if(component.isEmpty()) {
            throw new NotFoundException("Component id not found - " + id);
        }
        return ResponseEntity.ok(componentService.inactiveComponent(component.get()));
    }

}
