import React, { useEffect } from "react";
import { useKeycloak } from "@react-keycloak/web";

export const CoffeeUserPage = () => {
  const { keycloak } = useKeycloak();
  useEffect(() => {
    setTimeout(() => {
      fetch("http://localhost:8080/servlet_war_exploded/current-user", {
        method: "GET",
        headers: {
          Accept: "application/json",
          Authorization: "Bearer " + keycloak.token,
        },
      })
        .then((r) => r.json())
        .then(console.log)
        .catch(console.error);
      fetch("http://localhost:8080/servlet_war_exploded/current-user")
        .then((r) => r.json())
        .then(console.log)
        .catch(console.error);
    }, 500);
  }, []);
  return <div>CoffeeUserPage</div>;
};
