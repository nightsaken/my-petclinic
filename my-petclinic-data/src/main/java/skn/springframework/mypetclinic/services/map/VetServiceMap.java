package skn.springframework.mypetclinic.services.map;

import org.springframework.stereotype.Service;
import skn.springframework.mypetclinic.model.Specialty;
import skn.springframework.mypetclinic.model.Vet;
import skn.springframework.mypetclinic.services.SpecialtyService;
import skn.springframework.mypetclinic.services.VetService;

import java.util.Set;
@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet obj) {
        if(obj.getSpecialties().size() > 0) {
            obj.getSpecialties().forEach(specialty ->  {
                if(specialty.getId() == null){
                    Specialty savedSpecialty = specialtyService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(obj);
    }

    @Override
    public void delete(Vet obj) {
        super.delete(obj);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
