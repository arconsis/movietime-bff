package com.arconsis.movietime.bff.endpoint.health

import org.eclipse.microprofile.health.HealthCheck
import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Liveness
import javax.enterprise.context.ApplicationScoped

@Liveness
@ApplicationScoped
class SimpleHealthCheck : HealthCheck {

    override fun call(): HealthCheckResponse {
        return HealthCheckResponse.up("MovieDbAPI health")
    }
}