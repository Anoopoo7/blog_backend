var deltaScriptId = "R1.0/delta/products.js";
db = db.getSiblingDB(schema);

var delta_script_status = db.ecom_delta_script_audit.findOne({
  _id: deltaScriptId,
});
if (delta_script_status == null) {
  db.ecom_delta_script_audit.insert({
    _id: deltaScriptId,
    date: new Date(),
    status: "ready",
  });
}
if (delta_script_status != null && delta_script_status.status == "completed") {
  print(`***** Allready completed. skipping ${deltaScriptId} *****`);
} else {
  print(`***** Running: ${deltaScriptId} *****`);
  db.ecom_delta_script_audit.update(
    {
      _id: deltaScriptId,
    },
    {
      $set: {
        status: "running",
      },
    }
  );
}

run_common();
if (env == "uat") {
  run_uat();
}
if (env == "prod") {
  run_production();
}

// start functions

function run_common() {}
function run_uat() {}
function run_production() {}


print(`*****   completed: ${deltaScriptId} *****`)
db.ecom_delta_script_audit.update(
  {
    _id: deltaScriptId,
  },
  {
    $set: {
      status: "completed",
    },
  }
);
