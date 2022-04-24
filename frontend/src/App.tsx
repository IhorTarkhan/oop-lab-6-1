import React from "react";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./Keycloak";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/HomPage";
import { CoffeeUserPage } from "./pages/CoffeeUserPage";
import { CoffeeAdminPage } from "./pages/CoffeeAdminPage";

export const App = () => {
  return (
    <ReactKeycloakProvider authClient={keycloak}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/coffee-user" element={<CoffeeUserPage />} />
          <Route path="/coffee-admin" element={<CoffeeAdminPage />} />
        </Routes>
      </BrowserRouter>
    </ReactKeycloakProvider>
  );
};
