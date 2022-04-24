import React, { useEffect } from "react";
import { useKeycloak } from "@react-keycloak/web";
import { useNavigate } from "react-router-dom";

export const Home = () => {
  const { keycloak } = useKeycloak();
  const navigate = useNavigate();

  useEffect(() => {
    setTimeout(() => {
      let roles = keycloak.realmAccess?.roles;
      if (!roles) {
        keycloak.login();
      } else if (roles.includes("coffee_user")) {
        navigate("coffee-user");
      } else if (roles.includes("coffee_admin")) {
        navigate("coffee-admin");
      } else {
        keycloak.login();
      }
    }, 500);
  }, []);

  return null;
};
