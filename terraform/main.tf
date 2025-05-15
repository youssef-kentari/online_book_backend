resource "null_resource" "create_kind_cluster" {
  provisioner "local-exec" {
    command = <<-EOT
      if kind get clusters | grep -q "^my_cluster$"; then
        kind delete cluster --name my_cluster
      fi
      kind create cluster --name my_cluster --config=../create_cluster.yml
    EOT
    interpreter = ["bash", "-c"]
  }

  triggers = {
    config_sha = filesha256("../create_cluster.yml")
  }
}
