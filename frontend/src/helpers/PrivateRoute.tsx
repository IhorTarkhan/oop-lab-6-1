import { useKeycloak } from "@react-keycloak/web";

const PrivateRoute = ({ children } : any) => {
  const { keycloak } = useKeycloak();

  const isLoggedIn = keycloak.authenticated;
  console.log(keycloak.realmAccess?.roles);
  console.log(keycloak);

  return isLoggedIn ? children : null;
};

export default PrivateRoute;
