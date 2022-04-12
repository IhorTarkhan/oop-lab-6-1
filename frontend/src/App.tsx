import React from "react";
import { makeStyles } from "@mui/styles";

const useStyles = makeStyles({
  root: {
    textAlign: "center",
  },
  header: {
    backgroundColor: "#282c34",
    minHeight: "100vh",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    fontSize: "calc(10px + 2vmin)",
    color: "white",
    margin: -8,
  },
  logo: {
    height: "40vmin",
    pointerEvents: "none",
  },
  link: {
    color: "#61dafb",
  },
});

function App() {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <header className={classes.header}>
        <img alt={"logo"} src={"images/logo.svg"} className={classes.logo} />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className={classes.link}
          href={"https://reactjs.org"}
          target={"_blank"}
          rel={"noopener noreferrer"}
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
