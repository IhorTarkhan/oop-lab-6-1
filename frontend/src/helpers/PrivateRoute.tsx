import { useKeycloak } from "@react-keycloak/web";

const PrivateRoute = ({ children } : any) => {
  const { keycloak } = useKeycloak();

  const isLoggedIn = keycloak.authenticated;
  console.log(keycloak.realmAccess?.roles);

  return isLoggedIn ? children : null;
};

export default PrivateRoute;
