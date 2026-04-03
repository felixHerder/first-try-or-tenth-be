package com.felixherder.firsttryortenthbe.vehicles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleDO, String> {
}
