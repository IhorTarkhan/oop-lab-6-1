import React, { useEffect } from "react";
import { useKeycloak } from "@react-keycloak/web";
import { useNavigate } from "react-router-dom";

export const CoffeeUserPage = () => {
  const { keycloak } = useKeycloak();
  const navigate = useNavigate();
  useEffect(() => {
    setTimeout(() => {
      let roles = keycloak.realmAccess?.roles;
      if (!roles || !roles.includes("coffee_user")) {
        navigate("/");
        return;
      }
      console.log("Bearer " + keycloak.token);
      fetch("http://localhost:8080/servlet_war_exploded/current-user", {
        method: "GET",
        headers: {
          Accept: "application/json",
          Origin: "http://localhost:3000",
          Authorization: "Bearer " + keycloak.token,
        },
      })
        .then((r) => r.json())
        .then(console.log)
        .catch(console.error);
    }, 500);
  }, []);
  return <div>CoffeeUserPage</div>;
};
