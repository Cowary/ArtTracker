package com.ruderu.arttracker.dbCase.ranobe;

import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.entity.ranobe.RanobeVolume;
import com.ruderu.arttracker.repo.ranobe.RanobeVolumeRepo;
import com.ruderu.arttracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RanobeVolumeCrud {

    @Autowired
    RanobeVolumeRepo ranobeVolumeRepo;
    @Autowired
    RanobeCrud ranobeCrud;

    public void save(RanobeVolume ranobeVolume, Ranobe ranobe) {
        ranobeVolume.setLastUpd(DateUtil.now());
        ranobe.setLastUpd(DateUtil.now());
        ranobeCrud.save(ranobe);
        ranobeVolume.setRanobeId(ranobe.getId());
        ranobeVolumeRepo.save(ranobeVolume);
    }

    public List<RanobeVolume> getAll(String status) {
        List<RanobeVolume> ranobeVolumes;
        if(status.equals("")) ranobeVolumes = ranobeVolumeRepo.findAll();
        else ranobeVolumes = ranobeVolumeRepo.findAllByStatus(status);

        fill(ranobeVolumes);
        return ranobeVolumes;
    }

    public RanobeVolume getById(long id) {
        return ranobeVolumeRepo.findById(id).orElseThrow();
    }

    public void delete(RanobeVolume ranobeVolume) {
        ranobeVolumeRepo.delete(ranobeVolume);
    }

    private void fill(List<RanobeVolume> volumes) {
        for (RanobeVolume ranobeVolume : volumes) {
            Ranobe ranobe = ranobeCrud.findById(ranobeVolume.getRanobeId());
            ranobeVolume.setCommonField(ranobe);
        }
    }
}
